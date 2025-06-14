package com.luckprinter.demo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luckjingle.printersdk.BuildConfig;
import com.luckjingle.printersdk.R;
import com.luckprinter.demo.bean.ButtonItem;
import com.luckprinter.demo.repository.CustomPrinterData;
import com.luckprinter.sdk_new.BtReceiver;
import com.luckprinter.sdk_new.PrinterStatus;
import com.luckprinter.sdk_new.PrinterUtil;
import com.luckprinter.sdk_new.bean.PrinterStatusData;
import com.luckprinter.sdk_new.callback.ILogFilter;
import com.luckprinter.sdk_new.callback.OnClientConnectionListener;
import com.luckprinter.sdk_new.callback.OnEventListener;
import com.luckprinter.sdk_new.callback.OnPrintCallback;
import com.luckprinter.sdk_new.callback.OnPrinterInfoCallback;
import com.luckprinter.sdk_new.callback.OnReceiveDeviceStatusListener;
import com.luckprinter.sdk_new.callback.ResultCallback;
import com.luckprinter.sdk_new.callback.UpdateListener;
import com.luckprinter.sdk_new.client.FileEventRecorder;
import com.luckprinter.sdk_new.device.BaseDevice;
import com.luckprinter.sdk_new.device.PrinterHelper;
import com.luckprinter.sdk_new.device.custom.ICustomPrinter;
import com.luckprinter.sdk_new.device.custom.PrinterCommand;
import com.luckprinter.sdk_new.device.custom.PrinterProperty;
import com.luckprinter.sdk_new.device.normal.base.BaseNormalDevice;
import com.luckprinter.sdk_new.scan.ClassicScanDeviceHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author huangxiaohui
 * @date 2023/4/11
 */
public class LuckPrinterSdkDemoActivity extends AppCompatActivity implements View.OnClickListener, OnClientConnectionListener, OnReceiveDeviceStatusListener, OnEventListener {
    public static final String TAG = "LuckPrinterSdkDemo";
    public static final int MAX_LOG_TEXT_LENGTH = 50000;
    public static final int REQCODE_PERMISSION = 10001;
    public static final int IMAGE_REQUEST_CODE = 10002;
    public static final int SELECT_UPDATE_FILE = 10003;
    private ClassicScanDeviceHelper scanDeviceHelper;
    private HashMap hashMapFramework;
    private List<DeviceItem> deviceList = new ArrayList<>();
    private NestedScrollView scrollLog;
    private TextView tvLog;
    private RecyclerView rv_list, rv_buttons;
    private ProgressBar pb_discovery;
    private Button btn_update;
    private Button btn_unpair;
    private TextView tv_message, tv_no_device, tv_log_clear;
    private PrinterDeviceAdapter deviceAdapter;

    private Uri imageUri;

    private String[] needPermissons = null;
    private TextView tv_photo_path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_printer_sdk_demo);
        scrollLog = findViewById(R.id.scroll_log);
        tvLog = findViewById(R.id.tv_log);
        rv_list = findViewById(R.id.rv_list);
        rv_buttons = findViewById(R.id.rv_buttons);
        btn_update = findViewById(R.id.btn_update);
        btn_unpair = findViewById(R.id.btn_unpair);
        tv_message = findViewById(R.id.tv_message);
        tv_no_device = findViewById(R.id.tv_no_device);
        pb_discovery = findViewById(R.id.pb_discovery);
        tv_log_clear = findViewById(R.id.tv_log_clear);

        btn_unpair.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        initButtons();
        scanDeviceHelper = new ClassicScanDeviceHelper(this);
        scanDeviceHelper.setScanDeviceListener(new BtReceiver.Listener() {
            @Override
            public void foundDev(int type, String name, String mac) {
                DeviceItem item = new DeviceItem(type, name, mac);
                if (!deviceList.contains(item)) {
                    deviceList.add(item);
                    deviceAdapter.notifyDataSetChanged();
                    refreshEmptyDeviceView();
                }
            }

            @Override
            public void startDiscovery() {
                pb_discovery.setVisibility(View.VISIBLE);
            }

            @Override
            public void finishDiscovery() {
                pb_discovery.setVisibility(View.GONE);
            }


        });
        scanDeviceHelper.init();
        initCustomPrinterInfo();

        ArrayList<String> permissons = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissons.add(Manifest.permission.BLUETOOTH_CONNECT);
            permissons.add(Manifest.permission.BLUETOOTH_SCAN);
        } else {
            permissons.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            permissons.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        needPermissons = new String[permissons.size()];
        for (int i = 0; i < permissons.size(); i++) {
            needPermissons[i] = permissons.get(i);
        }

        if (!isBluePermissionGranted()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(needPermissons, REQCODE_PERMISSION);
            }
        } else {
            initPrinterList();
            startScan();
        }

        PrinterHelper.getInstance().addConnectListener(this);
        PrinterHelper.getInstance().addDeviceStatusListener(this);
        PrinterHelper.getInstance().addEventListener(this);
        PrinterHelper.getInstance().addDeviceForbiddenListener(() -> {
            PrinterUtil.showToast("设备被禁用");
        });
        PrinterHelper.getInstance().setLogFilter(new ILogFilter() {
            @Override
            public void onLog(String log) {
                tvLog.post(() -> {
                    if (tvLog.length() > MAX_LOG_TEXT_LENGTH) {
                        tvLog.setText("");
                    }
                    tvLog.append(log + "\n");
                    scrollLog.post(() -> {
                        scrollLog.scrollTo(0, tvLog.getMeasuredHeight());
                    });
                });
            }
        });

        tv_log_clear.setOnClickListener(v -> {
            tvLog.setText("");
        });
        initFirmWareMap();
    }

    private void startScan() {
        if (!checkLocationEnable()) {
            showToast("定位未打开");
            return;
        }
        if (scanDeviceHelper != null) {
            scanDeviceHelper.startScanDevice();
        }
    }

    private boolean checkLocationEnable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return true;
        } else {
            int locationMode = 0;
            try {
                locationMode = Settings.Secure.getInt(getContentResolver(),
                        Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        }
    }

    private void initCustomPrinterInfo() {
        HashMap<String, PrinterProperty> customPropertyMap = CustomPrinterData.getInstance().getCustomPropertyMap();
        PrinterHelper.getInstance().setCustomPropertyMap(customPropertyMap);
    }

    private void setCustomPrinterProperty() {
        BaseDevice device = PrinterHelper.getInstance().getPrinterDevice();
        if (device instanceof ICustomPrinter) {
            HashMap<String, PrinterProperty> customPropertyMap = CustomPrinterData.getInstance().getCustomPropertyMap();
            HashMap<String, PrinterCommand> customCommandMap = CustomPrinterData.getInstance().getCustomCommandMap();

            String namePrefix = PrinterHelper.getInstance().getNamePrefix();
            PrinterProperty property = customPropertyMap.get(namePrefix);
            PrinterCommand command = customCommandMap.get(namePrefix);
            ((ICustomPrinter) device).setProperty(property);
            ((ICustomPrinter) device).setCommand(command);
        }
    }

    private void initFirmWareMap() {
        hashMapFramework = new HashMap();
        hashMapFramework.put("LuckP_D1S", "d1s_1_23.BIN");
        hashMapFramework.put("DP_D1S", "B58K_BR8551_v3.11.bin");
        hashMapFramework.put("DP_D1", "B58K_BR8551_v1.26.bin");
        hashMapFramework.put("LuckP_D1X", "VTR_58D1_APP_YL2V1_0_18_230807.bin");
        hashMapFramework.put("LuckP_L2", "L2S-RD V1.05.BIN");
        hashMapFramework.put("DP_A80S", "L82S-HD-V1.02.BIN");
        hashMapFramework.put("ITP02", "A46_ACM_1.3.5_ITP05_240424.bin");
        hashMapFramework.put("DP_D80H", "GD88_ACM_1.3.9_DP-D80H_300DPI_240626.bin");
    }

    private void refreshEmptyDeviceView() {
        if (deviceList.size() > 0) {
            tv_no_device.setVisibility(View.GONE);
        } else {
            tv_no_device.setVisibility(View.VISIBLE);
        }
    }

    private void initButtons() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        rv_buttons.setLayoutManager(manager);

        List<ButtonItem> buttons = new ArrayList<>();
        MenuTypeEnum[] list = MenuTypeEnum.values();
        for (MenuTypeEnum item : list) {
            buttons.add(new ButtonItem(item.getType(), item.getName()));
        }

        ButtonAdapter adapter = new ButtonAdapter(buttons);
        rv_buttons.setAdapter(adapter);

        adapter.setItemClickListener(new ButtonAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ButtonItem btn) {
                String tag = btn.getTag();
                setMessageContent("");
                if (tag != null) {
                    MenuTypeEnum type = MenuTypeEnum.getByType(tag);
                    if (type != MenuTypeEnum.SCAN) {
                        if (!PrinterHelper.getInstance().isConnectedLuck()) {
                            PrinterUtil.showToast("设备未连接");
//                            return;
                        }
                    }

                    switch (type) {
                        //扫描打印机设备
                        case SCAN:
                            reScan();
                            break;
                        //连续纸打印图片
                        case PRINT: {
                            showPrintBitmapDialog(false);
                            break;
                        }
                        //缝隙纸打印图片
                        case PRINT_LABEL: {
                            showPrintBitmapDialogTag();
                        }
                        break;
                        case PRINT_BLACK_LABEL: {
                            showBlackPrintBitmapDialogTag();
                        }
                        break;
                        //缝隙纸打印图片
                        case PRINT_SHEET_LABEL: {
                            showPrintBitmapDialogSheetLabel();
                        }
                        break;
                        //a4折叠纸打印图片
                        case PRINT_A4_FOLDER: {
                            showPrintBitmapDialogFolder();
                        }
                        break;
                        //a4纹身纸
                        case PRINT_TATTOO: {
                            showPrintBitmapDialogTattoo();
                        }
                        break;
                        //设备型号
                        case PRINTER_MODEL_LUCK:
                            PrinterHelper.getInstance().printerModelLuck(new ResultCallback<String>() {

                                @Override
                                public void onSuccess(String data) {
                                    setMessageContent("型号：" + data);
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("型号获取失败");
                                }
                            });
                            break;
                        //设备型号
                        case PRINTER_BOOT_LUCK:
                            PrinterHelper.getInstance().printerBootLuck(new ResultCallback<String>() {

                                @Override
                                public void onSuccess(String data) {
                                    setMessageContent("Boot 版本 : " + data);
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("Boot 版本 获取失败");
                                }
                            });
                            break;
                        //SN号码
                        case PRINTER_SN_LUCK:
                            PrinterHelper.getInstance().printerSNLuck(new ResultCallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    setMessageContent("SN: " + data);
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("SN获取失败");
                                }
                            });

                            break;
                        //SN号码
                        case GET_ALL_INFO:
                            StringBuilder sb = new StringBuilder();
                            PrinterHelper.getInstance().getAllInfo(new OnPrinterInfoCallback() {
                                @Override
                                public void onStart() {

                                }

                                @Override
                                public void onVersion(String ver) {
                                    sb.append("[version]: ").append(ver).append(" ");
                                }

                                @Override
                                public void onName(String name) {
                                    sb.append("[name]: ").append(name).append(" ");
                                }

                                @Override
                                public void onMac(String mac) {
                                    sb.append("[mac]: ").append(mac).append(" ");
                                }

                                @Override
                                public void onSn(String sn) {
                                    sb.append("[sn]: ").append(sn).append(" ");
                                }

                                @Override
                                public void onModel(String model) {
                                    sb.append("[model]: ").append(model).append(" ");
                                }

                                @Override
                                public void onBattery(String battery) {
                                    sb.append("[battery]: ").append(battery).append("% ");
                                }

                                @Override
                                public void onShutTime(int shutTime) {
                                    sb.append("[shutTime]: ").append(shutTime).append("min ");
                                }

                                @Override
                                public void onDensity(int density) {
                                    sb.append("[density]: ").append(density).append(" ");
                                }

                                @Override
                                public void onFinish() {
                                    setMessageContent(sb.toString());
                                }
                            });

                            break;
                        //固件版本
                        case PRINTER_VERSION_LUCK:
                            PrinterHelper.getInstance().printerVersionLuck(new ResultCallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    setMessageContent("固件版本：" + data);
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("固件版本获取失败");
                                }
                            });
                            break;
                        //查自动关机时间
                        case GET_SHUT_TIME:
                            PrinterHelper.getInstance().getShutTimeLuck(new ResultCallback<Integer>() {
                                @Override
                                public void onSuccess(Integer data) {
                                    setMessageContent("自动关机时间：" + data + "分钟");
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("自动关机时间获取失败");
                                }
                            });
                            break;
                        //状态
                        case PRINTER_STATUS_LUCK:
                            PrinterHelper.getInstance().getPrinterStatus(new ResultCallback<PrinterStatusData>() {
                                @Override
                                public void onSuccess(PrinterStatusData data) {
                                    String result = String.format("状态 >> 打印:%d,舱盖:%d,缺纸%d,缺电%d,过热%d",
                                            data.getIsPrinting(), data.getIsOpen(), data.getIsLackPaper(), data.getIsLackElec(), data.getIsOverheat());
                                    setMessageContent(result);
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("状态获取失败");
                                }
                            });
                            break;
                        //电量
                        case PRINTER_BATTERY_LLUCK:
                            PrinterHelper.getInstance().getBatteryLuck(new ResultCallback<Integer>() {
                                @Override
                                public void onSuccess(Integer data) {
                                    String result = String.format("电量:%d%%", data);
                                    setMessageContent(result);
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("电量获取失败");
                                }
                            });
                            break;
                        //浓度参数
                        case GET_DENSITY:
                            PrinterHelper.getInstance().getDensityLuck(new ResultCallback<Integer>() {
                                @Override
                                public void onSuccess(Integer data) {
                                    String result = String.format("浓度:%d", data);
                                    setMessageContent(result);
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("浓度获取失败");
                                }

                            });
                            break;
                        //设置浓度
                        case SET_DENSITY: {
                            EditText et = new EditText(LuckPrinterSdkDemoActivity.this);
                            et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
                            et.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                            et.setPadding(PrinterUtil.dp2px(10), et.getPaddingTop(), et.getPaddingRight(), et.getPaddingBottom());
                            new AlertDialog.Builder(LuckPrinterSdkDemoActivity.this)
                                    .setTitle("请输入浓度")
                                    .setView(et)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            int value = 0;
                                            try {
                                                value = Integer.parseInt(et.getText().toString());
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                            }
                                            PrinterHelper.getInstance().setDensityLuck(value, new ResultCallback<Integer>() {
                                                @Override
                                                public void onSuccess(Integer data) {
                                                    setMessageContent("浓度设置成功");
                                                }

                                                @Override
                                                public void onFail() {
                                                    setMessageContent("浓度设置失败");
                                                }
                                            });
                                        }
                                    }).setNegativeButton("取消", null)
                                    .show();
                        }
                        break;
                        //速度参数
                        case GET_SPEED:
                            PrinterHelper.getInstance().getSpeed(new ResultCallback<Integer>() {
                                @Override
                                public void onSuccess(Integer data) {
                                    String result = String.format("速度:%d", data);
                                    setMessageContent(result);
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("速度获取失败");
                                }

                            });
                            break;
                        //设置速度
                        case SET_SPEED: {
                            EditText et = new EditText(LuckPrinterSdkDemoActivity.this);
                            et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
                            et.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                            et.setPadding(PrinterUtil.dp2px(10), et.getPaddingTop(), et.getPaddingRight(), et.getPaddingBottom());
                            new AlertDialog.Builder(LuckPrinterSdkDemoActivity.this)
                                    .setTitle("请输入速度(0~8)")
                                    .setView(et)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            int value = 0;
                                            try {
                                                value = Integer.parseInt(et.getText().toString());
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                            }
                                            PrinterHelper.getInstance().setSpeedLuck(value, new ResultCallback<Integer>() {
                                                @Override
                                                public void onSuccess(Integer data) {
                                                    setMessageContent("速度设置成功");
                                                }

                                                @Override
                                                public void onFail() {
                                                    setMessageContent("速度设置失败");
                                                }

                                            });
                                        }
                                    }).setNegativeButton("取消", null)
                                    .show();
                        }
                        break;
                        //设置自动关机时间
                        case SET_SHUTTIME_LUCK: {
                            EditText et = new EditText(LuckPrinterSdkDemoActivity.this);
                            et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
                            et.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                            et.setPadding(PrinterUtil.dp2px(10), et.getPaddingTop(), et.getPaddingRight(), et.getPaddingBottom());
                            new AlertDialog.Builder(LuckPrinterSdkDemoActivity.this)
                                    .setTitle("请输入自动关机时间（分钟）")
                                    .setView(et)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            int value = 0;
                                            try {
                                                value = Integer.parseInt(et.getText().toString());
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                            }
                                            if (value < 0) {
                                                value = 0;
                                            }
                                            PrinterHelper.getInstance().setShutTimeLuck(value, new ResultCallback<Integer>() {
                                                @Override
                                                public void onSuccess(Integer data) {
                                                    setMessageContent("自动关机时间设置成功");
                                                }

                                                @Override
                                                public void onFail() {
                                                    setMessageContent("自动关机时间设置失败");
                                                }
                                            });
                                        }
                                    }).setNegativeButton("取消", null)
                                    .show();
                        }

                        break;

                        //出厂设置
                        case SET_RECOVERY_LUCK:
                            PrinterHelper.getInstance().setRecoveryLuck(new ResultCallback<Integer>() {
                                @Override
                                public void onSuccess(Integer data) {
                                    setMessageContent("恢复出厂设置成功");
                                }

                                @Override
                                public void onFail() {
                                    setMessageContent("恢复出厂设置失败");
                                }
                            });
                            break;
                        case GO_PAPER:
                            PrinterHelper.getInstance().printLineDotsLuck(144);
                            break;
                        case REVERSE_GO_PAPER:
                            PrinterHelper.getInstance().printReverseLineDotsLuck(144);
                            break;
                        case PACK_ERROR_LOG:
                            String errorZipFile = FileEventRecorder.getInstance().getPrinterErrorZipFile();
                            showToast((errorZipFile != null)?"生成成功":"生成失败");
                            if (errorZipFile != null) {
                                sendErrorLogToEmail(new File(errorZipFile));
                            }
                            break;
                        case PRINTER_SETTING_LUCK:
                            PrinterHelper.getInstance().printerSettingLuck(new ResultCallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    setMessageContent("md5: " + data);
                                }

                                @Override
                                public void onFail() {

                                }
                            });
                            break;

                        case TEST:
                            doTest();
                            break;

                    }
                }
            }
        });
    }

    /**
     * 通过邮件发送连接日志
     * @param attachmentFile
     */
    private void sendErrorLogToEmail(File attachmentFile) {
        String email = "mack@luckjingle.com";
        Context context = this;
        String subject = "bugReport";
        String message = "bugReport";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        Uri attachmentUri = FileProvider.getUriForFile(context, getPackageName() + ".fileprovider", attachmentFile);
        emailIntent.putExtra(Intent.EXTRA_STREAM, attachmentUri);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    /**
     * 打印连续纸图片对话框
     */
    private void showPrintBitmapDialog(boolean once) {
        View view = LayoutInflater.from(LuckPrinterSdkDemoActivity.this).inflate(R.layout.layout_print_bitmap_continue, null);
        EditText et = view.findViewById(R.id.et_content);
        EditText et_width = view.findViewById(R.id.et_width);
        LinearLayout llayout_select_photo = view.findViewById(R.id.llayout_select_photo);
        tv_photo_path = view.findViewById(R.id.tv_photo_path);
        llayout_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isKitKatO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
                Intent getAlbum;
                if (isKitKatO) {
                    getAlbum = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                } else {
                    getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                }
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum, IMAGE_REQUEST_CODE);
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(LuckPrinterSdkDemoActivity.this)
                .setTitle("打印图片")
                .setView(view)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                tv_photo_path = null;
                imageUri = null;
            }
        });
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri == null) {
                    PrinterUtil.showToast("请选择图片");
                    return;
                }
                int count = 1;
                try {
                    count = Integer.parseInt(et.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (count < 1) {
                    count = 1;
                }

                Integer widthMM = null;
                try {
                    widthMM = Integer.parseInt(et_width.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                ContentResolver resolver = getContentResolver();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, imageUri);
                    printImg(bitmap, count, widthMM, once);
                } catch (IOException e) {
                    PrinterUtil.showToast("图片获取失败");
                }

                dialog.dismiss();
            }
        });
    }

    /**
     * 打印缝隙纸图片对话框
     */
    private void showPrintBitmapDialogTag() {
        View view = LayoutInflater.from(LuckPrinterSdkDemoActivity.this).inflate(R.layout.layout_print_bitmap_label, null);
        EditText et = view.findViewById(R.id.et_content);
        EditText et_width = view.findViewById(R.id.et_width);
        EditText et_height = view.findViewById(R.id.et_height);

        int tagWidth = SPUtil.getTagWidth();
        int tagHeight = SPUtil.getTagHeight();
        if (tagWidth > 0) {
            et_width.setText(tagWidth + "");
        }
        if (tagHeight > 0) {
            et_height.setText(tagHeight + "");
        }
        LinearLayout llayout_select_photo = view.findViewById(R.id.llayout_select_photo);
        tv_photo_path = view.findViewById(R.id.tv_photo_path);
        llayout_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isKitKatO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
                Intent getAlbum;
                if (isKitKatO) {
                    getAlbum = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                } else {
                    getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                }
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum, IMAGE_REQUEST_CODE);
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(LuckPrinterSdkDemoActivity.this)
                .setTitle("打印图片")
                .setView(view)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                tv_photo_path = null;
                imageUri = null;
            }
        });
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri == null) {
                    PrinterUtil.showToast("请选择图片");
                    return;
                }
                int count = 1;
                try {
                    count = Integer.parseInt(et.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (count < 1) {
                    count = 1;
                }
                int width = 50;
                try {
                    width = Integer.parseInt(et_width.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                int height = 30;
                try {
                    height = Integer.parseInt(et_height.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                SPUtil.putTagWidth(width);
                SPUtil.putTagHeight(height);

                ContentResolver resolver = getContentResolver();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, imageUri);
                    int printWidth = width * (PrinterHelper.getInstance().is304Dpi() ? 12 : 8);
                    int printMaxWidth = PrinterHelper.getInstance().getPrintMaxWidth();
                    if (printWidth > printMaxWidth) {
                        printWidth = printMaxWidth;
                    }
                    int printHeight = (int) (printWidth * ((height * 1.0f) / width));

                    printTag(bitmap, printWidth, printHeight, count);
                } catch (IOException e) {
                    PrinterUtil.showToast("图片获取失败");
                }

                dialog.dismiss();
            }
        });
    }
    private void showBlackPrintBitmapDialogTag() {
        View view = LayoutInflater.from(LuckPrinterSdkDemoActivity.this).inflate(R.layout.layout_print_bitmap_label, null);
        EditText et = view.findViewById(R.id.et_content);
        EditText et_width = view.findViewById(R.id.et_width);
        EditText et_height = view.findViewById(R.id.et_height);
        int tagWidth = SPUtil.getTagBlackWidth();
        int tagHeight = SPUtil.getTagBlackHeight();
        if (tagWidth > 0) {
            et_width.setText(tagWidth + "");
        }
        if (tagHeight > 0) {
            et_height.setText(tagHeight + "");
        }
        LinearLayout llayout_select_photo = view.findViewById(R.id.llayout_select_photo);
        tv_photo_path = view.findViewById(R.id.tv_photo_path);
        llayout_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isKitKatO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
                Intent getAlbum;
                if (isKitKatO) {
                    getAlbum = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                } else {
                    getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                }
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum, IMAGE_REQUEST_CODE);
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(LuckPrinterSdkDemoActivity.this)
                .setTitle("打印图片")
                .setView(view)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                tv_photo_path = null;
                imageUri = null;
            }
        });
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri == null) {
                    PrinterUtil.showToast("请选择图片");
                    return;
                }
                int count = 1;
                try {
                    count = Integer.parseInt(et.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (count < 1) {
                    count = 1;
                }
                int width = 50;
                try {
                    width = Integer.parseInt(et_width.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                int height = 30;
                try {
                    height = Integer.parseInt(et_height.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }


                SPUtil.putTagBlackWidth(width);
                SPUtil.putTagBlackHeight(height);

                ContentResolver resolver = getContentResolver();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, imageUri);
                    int printWidth = width * (PrinterHelper.getInstance().is304Dpi() ? 12 : 8);
                    int printMaxWidth = PrinterHelper.getInstance().getPrintMaxWidth();
                    if (printWidth > printMaxWidth) {
                        printWidth = printMaxWidth;
                    }
                    int printHeight = (int) (printWidth * ((height * 1.0f) / width));


                    printBlackTag(bitmap, printWidth, printHeight, count);
                } catch (IOException e) {
                    PrinterUtil.showToast("图片获取失败");
                }

                dialog.dismiss();
            }
        });}
    private void showPrintBitmapDialogSheetLabel() {
        View view = LayoutInflater.from(LuckPrinterSdkDemoActivity.this).inflate(R.layout.layout_print_bitmap_sheet_label, null);
        EditText et = view.findViewById(R.id.et_content);
        EditText et_width = view.findViewById(R.id.et_width);
        EditText et_height = view.findViewById(R.id.et_height);
        EditText et_speed = view.findViewById(R.id.et_speed);
        EditText et_density = view.findViewById(R.id.et_density);
        LinearLayout llayout_select_photo = view.findViewById(R.id.llayout_select_photo);
        tv_photo_path = view.findViewById(R.id.tv_photo_path);
        llayout_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isKitKatO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
                Intent getAlbum;
                if (isKitKatO) {
                    getAlbum = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                } else {
                    getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                }
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum, IMAGE_REQUEST_CODE);
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(LuckPrinterSdkDemoActivity.this)
                .setTitle("打印图片")
                .setView(view)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                tv_photo_path = null;
                imageUri = null;
            }
        });
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri == null) {
                    PrinterUtil.showToast("请选择图片");
                    return;
                }
                int count = 1;
                try {
                    count = Integer.parseInt(et.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (count < 1) {
                    count = 1;
                }
                int width = 76;
                try {
                    width = Integer.parseInt(et_width.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                int height = 130;
                try {
                    height = Integer.parseInt(et_height.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                int speed = 8;
                try {
                    speed = Integer.parseInt(et_speed.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int density = 15;
                try {
                    density = Integer.parseInt(et_density.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ContentResolver resolver = getContentResolver();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, imageUri);
                    int printWidth = width * (PrinterHelper.getInstance().is304Dpi() ? 12 : 8) - 1;
                    int printHeight = (int) (printWidth * ((height * 1.0f) / width));

                    printImgSheetLabel(bitmap, width, height, printWidth,
                            printHeight, speed, density, count);
                } catch (IOException e) {
                    PrinterUtil.showToast("图片获取失败");
                }

                dialog.dismiss();
            }
        });
    }

    /**
     * 缝隙纸打印
     */
    private void printImgSheetLabel(Bitmap bitmap, int widthMM, int heightMM,
                                    int width, int height, int speed, int density, int count) {
        PrinterHelper.getInstance().getPrinterStatus(new ResultCallback<PrinterStatusData>() {
            @Override
            public void onSuccess(PrinterStatusData data) {
                int errorCode = -1;
                if (data.getIsLackElec() == 1) {
                    errorCode = PrinterStatus.PRINTER_STATUS_LOWVAL;
                }
                if (data.getIsLackPaper() == 1) {
                    errorCode = PrinterStatus.PRINTER_STATUS_OUTPAPER;
                }
                if (data.getIsOpen() == 1) {
                    errorCode = PrinterStatus.PRINTER_STATUS_OPENCOVER;
                }
                if (data.getIsOverheat() == 1) {
                    errorCode = PrinterStatus.PRINTER_STATUS_OVERHEAT;
                }
                if (errorCode >= 0) {
                    showStatusToast(errorCode);
                } else {
                    Bitmap resizeBitmap;
                    resizeBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
                    if (bitmap != resizeBitmap) {
                        bitmap.recycle();
                    }
                    resizeBitmap = OpenCVUtils.getInstance().getFlyodBitmapNew(resizeBitmap);
                    PrinterHelper.getInstance().printSheetLabel(widthMM, heightMM, speed,
                            density, resizeBitmap, count);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    /**
     * 打印a4折叠纸纸图片对话框
     */
    private void showPrintBitmapDialogFolder() {
        View view = LayoutInflater.from(LuckPrinterSdkDemoActivity.this).inflate(R.layout.layout_print_bitmap_a4_folder, null);
        EditText et = view.findViewById(R.id.et_content);
        LinearLayout llayout_select_photo = view.findViewById(R.id.llayout_select_photo);
        tv_photo_path = view.findViewById(R.id.tv_photo_path);
        RadioGroup rg_a4_size = view.findViewById(R.id.rg_a4_size);
        llayout_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isKitKatO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
                Intent getAlbum;
                if (isKitKatO) {
                    getAlbum = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                } else {
                    getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                }
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum, IMAGE_REQUEST_CODE);
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(LuckPrinterSdkDemoActivity.this)
                .setTitle("打印图片")
                .setView(view)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                tv_photo_path = null;
                imageUri = null;
            }
        });
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri == null) {
                    PrinterUtil.showToast("请选择图片");
                    return;
                }
                int count = 1;
                try {
                    count = Integer.parseInt(et.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (count < 1) {
                    count = 1;
                }

                ContentResolver resolver = getContentResolver();
                try {

                    RadioButton rb = rg_a4_size.findViewById(rg_a4_size.getCheckedRadioButtonId());
                    String a4SizeText = (String) rb.getTag();
                    String[] a4Size = a4SizeText.split("x");
                    PrinterHelper.getInstance().setA4PaperSize(
                            Integer.parseInt(a4Size[0]),
                            Integer.parseInt(a4Size[1])
                    );

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, imageUri);
                    printImgA4Folder(bitmap, count);
                } catch (Exception e) {
                    PrinterUtil.showToast("图片获取失败");
                }

                dialog.dismiss();
            }
        });
    }


    /**
     * 打印a4纹身纸图片对话框
     */
    private void showPrintBitmapDialogTattoo() {
        View view = LayoutInflater.from(LuckPrinterSdkDemoActivity.this).inflate(R.layout.layout_print_bitmap_a4_folder, null);
        EditText et = view.findViewById(R.id.et_content);
        LinearLayout llayout_select_photo = view.findViewById(R.id.llayout_select_photo);
        tv_photo_path = view.findViewById(R.id.tv_photo_path);
        RadioGroup rg_a4_size = view.findViewById(R.id.rg_a4_size);
        llayout_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isKitKatO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
                Intent getAlbum;
                if (isKitKatO) {
                    getAlbum = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                } else {
                    getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                }
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum, IMAGE_REQUEST_CODE);
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(LuckPrinterSdkDemoActivity.this)
                .setTitle("打印图片")
                .setView(view)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                tv_photo_path = null;
                imageUri = null;
            }
        });
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri == null) {
                    PrinterUtil.showToast("请选择图片");
                    return;
                }
                int count = 1;
                try {
                    count = Integer.parseInt(et.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (count < 1) {
                    count = 1;
                }

                ContentResolver resolver = getContentResolver();
                try {

                    RadioButton rb = rg_a4_size.findViewById(rg_a4_size.getCheckedRadioButtonId());
                    String a4Size = (String) rb.getTag();
                    String[] a4SizeArray = a4Size.split("x");
                    int width = Integer.parseInt(a4SizeArray[0]);
                    int height = Integer.parseInt(a4SizeArray[1]);
                    PrinterHelper.getInstance().setA4PaperSize(width, height);

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, imageUri);
                    printImgA4Tattoo(bitmap, count);
                } catch (Exception e) {
                    PrinterUtil.showToast("图片获取失败");
                }

                dialog.dismiss();
            }
        });
    }

    /**
     * 连续纸打印
     *
     * @param bitmap
     * @param count
     * @param widthMM 设置的打印宽度，单位mm
     */
    private void printImg(Bitmap bitmap, int count, Integer widthMM, boolean once) {
        Integer requireWidth = null;
        if (widthMM != null) {
            if (widthMM < 10) {
                widthMM = 10;
            }
            requireWidth = (PrinterHelper.getInstance().is304Dpi() ? 12 : 8) * widthMM;
        }

        Bitmap resizeBitmap;
        int printWidth = PrinterHelper.getInstance().getPrintWidth();
        if (requireWidth != null) {
            printWidth = Math.min(printWidth, requireWidth);
        }

        float scale = printWidth * 1.0f / bitmap.getWidth();
        int printHeight = (int) (scale * bitmap.getHeight());

        resizeBitmap = Bitmap.createScaledBitmap(bitmap, printWidth, printHeight, true);
        if (bitmap != resizeBitmap) {
            bitmap.recycle();
        }
        resizeBitmap = OpenCVUtils.getInstance().getFlyodBitmapNew(resizeBitmap);
        OnPrintCallback callback = new OnPrintCallback() {
            @Override
            public void onStartPrint() {
                PrinterUtil.showToast("开始打印");
            }

            @Override
            public void onPrinting(int page, int num) {
                PrinterUtil.showToast("正在打印..." + page + "/" + num);
            }

            @Override
            public void onPrintIndexStart(Bitmap bitmap, int page, int num) {
                Log.d(TAG, "print start ..." + page + "/" + num);
            }

            @Override
            public void onPrintIndexEnd(Bitmap bitmap, int page, int num) {
                Log.d(TAG, "print end ..." + page + "/" + num);
            }

            @Override
            public void onPrintSuccess() {
                PrinterUtil.showToast("完成打印");
            }

            @Override
            public void onPrintFail(int status) {
                showStatusToast(status);
            }
        };

        if (once && count == 1) {
            PrinterHelper.getInstance().printOnce(resizeBitmap, 1, 1, new ResultCallback<Integer>() {
                @Override
                public void onSuccess(Integer data) {
                    PrinterUtil.showToast("完成打印");
                }

                @Override
                public void onFail() {

                }
            });
        } else {
            PrinterHelper.getInstance().print(resizeBitmap, count, callback);
        }

    }

    /**
     * 连续纸打印
     *
     * @param bitmap
     * @param count
     * @param widthMM 设置的打印宽度，单位mm
     */
    private void printImgGray(Bitmap bitmap, int count, Integer widthMM) {
        Integer requireWidth = null;
        if (widthMM != null) {
            if (widthMM < 10) {
                widthMM = 10;
            }
            requireWidth = (PrinterHelper.getInstance().is304Dpi() ? 12 : 8) * widthMM;
        }

        Bitmap resizeBitmap;
        int printWidth = PrinterHelper.getInstance().getPrintWidth();
        if (requireWidth != null) {
            printWidth = Math.min(printWidth, requireWidth);
        }

        float scale = printWidth * 1.0f / bitmap.getWidth();
        int printHeight = (int) (scale * bitmap.getHeight());

        resizeBitmap = Bitmap.createScaledBitmap(bitmap, printWidth, printHeight, true);
        if (bitmap != resizeBitmap) {
            bitmap.recycle();
        }
        resizeBitmap = OpenCVUtils.getInstance().getFlyodBitmapNew(resizeBitmap);
        OnPrintCallback callback = new OnPrintCallback() {
            @Override
            public void onStartPrint() {
                PrinterUtil.showToast("开始打印");
            }

            @Override
            public void onPrinting(int page, int num) {
                PrinterUtil.showToast("正在打印..." + page + "/" + num);
            }

            @Override
            public void onPrintIndexStart(Bitmap bitmap, int page, int num) {
                Log.d(TAG, "print start ..." + page + "/" + num);
            }

            @Override
            public void onPrintIndexEnd(Bitmap bitmap, int page, int num) {
                Log.d(TAG, "print end ..." + page + "/" + num);
            }

            @Override
            public void onPrintSuccess() {
                PrinterUtil.showToast("完成打印");
            }

            @Override
            public void onPrintFail(int status) {
                showStatusToast(status);
            }
        };

        PrinterHelper.getInstance().print(resizeBitmap, count, callback);
    }

    /**
     * 缝隙纸打印
     */
    private void printBlackTag(Bitmap bitmap, int width, int height, int count) {
        Bitmap resizeBitmap;
        resizeBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        if (bitmap != resizeBitmap) {
            bitmap.recycle();
        }
        resizeBitmap = OpenCVUtils.getInstance().getFlyodBitmapNew(resizeBitmap);
        OnPrintCallback callback = new OnPrintCallback() {
            @Override
            public void onStartPrint() {
                PrinterUtil.showToast("开始打印");
            }

            @Override
            public void onPrinting(int page, int num) {
                PrinterUtil.showToast("正在打印..." + page + "/" + num);
            }

            @Override
            public void onPrintIndexStart(Bitmap bitmap, int page, int num) {
                Log.d(TAG, "print start ..." + page + "/" + num);
            }

            @Override
            public void onPrintIndexEnd(Bitmap bitmap, int page, int num) {
                Log.d(TAG, "print end ..." + page + "/" + num);
            }

            @Override
            public void onPrintSuccess() {
                PrinterUtil.showToast("完成打印");
            }

            @Override
            public void onPrintFail(int status) {
                showStatusToast(status);
            }
        };

        PrinterHelper.getInstance().printBlackTag(resizeBitmap, count, callback);
    }
    /**
     * 缝隙纸打印
     */
    private void printTag(Bitmap bitmap, int width, int height, int count) {
        Bitmap resizeBitmap;
        resizeBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        if (bitmap != resizeBitmap) {
            bitmap.recycle();
        }
        resizeBitmap = OpenCVUtils.getInstance().getFlyodBitmapNew(resizeBitmap);
        OnPrintCallback callback = new OnPrintCallback() {
            @Override
            public void onStartPrint() {
                PrinterUtil.showToast("开始打印");
            }

            @Override
            public void onPrinting(int page, int num) {
                PrinterUtil.showToast("正在打印..." + page + "/" + num);
            }

            @Override
            public void onPrintIndexStart(Bitmap bitmap, int page, int num) {
                Log.d(TAG, "print start ..." + page + "/" + num);
            }

            @Override
            public void onPrintIndexEnd(Bitmap bitmap, int page, int num) {
                Log.d(TAG, "print end ..." + page + "/" + num);
            }

            @Override
            public void onPrintSuccess() {
                PrinterUtil.showToast("完成打印");
            }

            @Override
            public void onPrintFail(int status) {
                showStatusToast(status);
            }
        };

        PrinterHelper.getInstance().printTag(resizeBitmap, count, callback);
    }


    /**
     * a4折叠纸连续纸打印
     */
    private void printImgA4Folder(Bitmap bitmap, int count) {
        Bitmap resizeBitmap;
        if (PrinterHelper.getInstance().isA4Printer()) {
            //A4一页一页打印
            int pageWidth = PrinterHelper.getInstance().getA4PrintWidth();
            int pageHeight = PrinterHelper.getInstance().getA4PrintHeight();
            float scale = Math.min(pageWidth * 1.0f / bitmap.getWidth(), pageHeight * 1.0f / bitmap.getHeight());
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            matrix.postTranslate((pageWidth - bitmap.getWidth() * scale) / 2, 0);
            resizeBitmap = Bitmap.createBitmap(pageWidth, pageHeight, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(resizeBitmap);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap, matrix, new Paint(Paint.ANTI_ALIAS_FLAG));
            if (resizeBitmap != bitmap) {
                bitmap.recycle();
            }
            resizeBitmap = OpenCVUtils.getInstance().getFlyodBitmapNew(resizeBitmap);
            OnPrintCallback callback = new OnPrintCallback() {
                @Override
                public void onStartPrint() {
                    PrinterUtil.showToast("开始打印");
                }

                @Override
                public void onPrinting(int page, int num) {
                    PrinterUtil.showToast("正在打印..." + page + "/" + num);
                }

                @Override
                public void onPrintIndexStart(Bitmap bitmap, int page, int num) {
                    Log.d(TAG, "print start ..." + page + "/" + num);
                }

                @Override
                public void onPrintIndexEnd(Bitmap bitmap, int page, int num) {
                    Log.d(TAG, "print end ..." + page + "/" + num);
                }

                @Override
                public void onPrintSuccess() {
                    PrinterUtil.showToast("完成打印");
                }

                @Override
                public void onPrintFail(int status) {
                    showStatusToast(status);
                }
            };
            PrinterHelper.getInstance().printFolder(resizeBitmap, count, callback);
        } else {
            PrinterUtil.showToast("设备不是A4打印机");
        }
    }
    /**
     * a4折叠纸连续纸打印
     */
    private void printImgA4Tattoo(Bitmap bitmap, int count) {
        Bitmap resizeBitmap;
        if (PrinterHelper.getInstance().isA4Printer()) {
            //A4一页一页打印
            int pageWidth = PrinterHelper.getInstance().getA4PrintWidth();
            int pageHeight = PrinterHelper.getInstance().getA4PrintHeight();
            float scale = Math.min(pageWidth * 1.0f / bitmap.getWidth(), pageHeight * 1.0f / bitmap.getHeight());
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            matrix.postTranslate((pageWidth - bitmap.getWidth() * scale) / 2, 0);
            resizeBitmap = Bitmap.createBitmap(pageWidth, pageHeight, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(resizeBitmap);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap, matrix, new Paint(Paint.ANTI_ALIAS_FLAG));
            if (resizeBitmap != bitmap) {
                bitmap.recycle();
            }
            resizeBitmap = OpenCVUtils.getInstance().getFlyodBitmapNew(resizeBitmap);
            OnPrintCallback callback = new OnPrintCallback() {
                @Override
                public void onStartPrint() {
                    PrinterUtil.showToast("开始打印");
                }

                @Override
                public void onPrinting(int page, int num) {
                    PrinterUtil.showToast("正在打印..." + page + "/" + num);
                }

                @Override
                public void onPrintIndexStart(Bitmap bitmap, int page, int num) {
                    Log.d(TAG, "print start ..." + page + "/" + num);
                }

                @Override
                public void onPrintIndexEnd(Bitmap bitmap, int page, int num) {
                    Log.d(TAG, "print end ..." + page + "/" + num);
                }

                @Override
                public void onPrintSuccess() {
                    PrinterUtil.showToast("完成打印");
                }

                @Override
                public void onPrintFail(int status) {
                    showStatusToast(status);
                }
            };
            PrinterHelper.getInstance().printTattoo(resizeBitmap, count, callback);
        } else {
            PrinterUtil.showToast("设备不是A4打印机");
        }
    }

    private void initPrinterList() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(manager);

        deviceAdapter = new PrinterDeviceAdapter(deviceList);
        rv_list.setAdapter(deviceAdapter);
        deviceAdapter.setItemClickListener(new PrinterDeviceAdapter.ItemClickListener() {
            @Override
            public void onItemClick(DeviceItem device) {
                setMessageContent("正在连接设备...");
                Executors.newCachedThreadPool().execute(() -> {

                    String deviceName = device.getName();
                    String deviceMac = device.getMac();

                    boolean result = PrinterHelper.getInstance().connectLuck(deviceName, deviceMac);
                    if (result) {
                        setCustomPrinterProperty();
                    }
                    BaseDevice printerDevice = PrinterHelper.getInstance().getPrinterDevice();
                    String deviceClassName = printerDevice.getClass().getSimpleName();
                    setMessageContent(result ? ("连接成功 " + deviceClassName) : "连接失败");
                    printTestPage(device);

//                    BleTest.connectBle(deviceMac);
                });
            }
        });
    }

    private void printTestPage(DeviceItem device) {
        if (PrinterHelper.getInstance().isConnectedLuck() && device.getName().equals("MPT-II")) {
            PrinterHelper.getInstance().sendCommand(new byte[]{0x12, 0x54}, new ResultCallback<Integer>() {
                @Override
                public void onSuccess(Integer data) {
                    PrinterUtil.showToast("打印测试页成功：" + data);
                }

                @Override
                public void onFail() {
                    PrinterUtil.showToast("打印测试页失败：");

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    try {
                        imageUri = data.getData();
                        if (tv_photo_path != null) {
                            String path = imageUri.getPath();
                            tv_photo_path.setText("图片：" + path);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case SELECT_UPDATE_FILE: {
                if (resultCode == RESULT_OK) {
                    if (!PrinterHelper.getInstance().isConnectedLuck()) {
                        PrinterUtil.runOnUi(new Runnable() {
                            @Override
                            public void run() {
                                PrinterUtil.showToast("设备未连接");
                            }
                        });
                        return;
                    }
                    Uri uri = data.getData();
                    String filePath = FileUriUtils.getFileAbsolutePath(this, uri);
                    if (filePath != null && filePath.toUpperCase().endsWith(".BIN")) {
                        doUpdate(filePath);
                    } else {
                        PrinterUtil.showToast("文件不是升级文件(BIN文件后缀)");
                    }
                }
            }
            break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scanDeviceHelper != null) {
            scanDeviceHelper.unInit();
            scanDeviceHelper = null;
        }

        PrinterHelper.getInstance().removeConnectListener(this);
        PrinterHelper.getInstance().removeDeviceStatusListener(this);
        PrinterHelper.getInstance().removeEventListener(this);
    }

    /**
     * 是否蓝牙权限都已授权
     *
     * @return
     */
    private boolean isBluePermissionGranted() {
        boolean isGranted = true;
        // Android 6.0动态请求权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String str : needPermissons) {
                if (checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    isGranted = false;
                    break;
                }
            }
        }
        return isGranted;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQCODE_PERMISSION) {
            if (isBluePermissionGranted()) {
                initPrinterList();
                startScan();
            } else {
                showToast("蓝牙相关权限未授权");
            }
        }
    }

    public void reScan() {
        deviceList.clear();
        if (deviceAdapter != null) {
            deviceAdapter.notifyDataSetChanged();
            refreshEmptyDeviceView();
            startScan();
        }
    }

    private void doUpdate(String filePath) {
        File file = new File(filePath);
        PrinterHelper.getInstance().updatePrinterLuck(file, new UpdateListener() {
            @Override
            public void onStart() {
                setMessageContent("正在升级...");
            }

            @Override
            public void onProgress(int progress) {
                setMessageContent(String.format("正在升级...%d%%", progress));
            }

            @Override
            public void onError() {
                setMessageContent("升级发生错误！");
            }

            @Override
            public void onComplete() {
                setMessageContent("升级成功！");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int vid = v.getId();
        if (vid == R.id.btn_update) {
            if (!PrinterHelper.getInstance().isConnectedLuck()) {
                Toast.makeText(this, "打印机未连接", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = PrinterHelper.getInstance().getPrinterDevice().getDeviceName();
            if (name.contains("_")) {
                name = name.substring(0, name.lastIndexOf("_"));
            }
            boolean contain = false;
            for (int i = 0; i < hashMapFramework.size(); i++) {
                if (hashMapFramework.containsKey(name)) {
                    contain = true;
                    break;
                }
            }
            if (contain) {
//                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.d1s_1_23);
                InputStream in = null;
                FileOutputStream fos = null;
                try {
                    File fi = new File(getFilesDir() + "/" + hashMapFramework.get(name).toString());
                    if (fi.exists()) {
                        fi.delete();
                    }
                    in = getAssets().open(hashMapFramework.get(name).toString());
                    fos = new FileOutputStream(getFilesDir() + "/" + hashMapFramework.get(name).toString());
                    int readCount = 0;
                    byte[] tem = new byte[256];
                    while ((readCount = in.read(tem)) != -1) {
                        fos.write(tem, 0, readCount);
                        fos.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                doUpdate(getFilesDir() + "/" + hashMapFramework.get(name).toString());

            } else {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, SELECT_UPDATE_FILE);
            }
        }
        if (vid == R.id.btn_unpair) {
            Intent intent = new Intent(this, BluetoothActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onDeviceStatus(int status) {
        showStatusToast(status);
    }

    @Override
    public void onLuckConnected(String name, String address) {
        Log.d(TAG, "onConnected: " + address);
        if (deviceAdapter != null) {
            deviceAdapter.notifyDataSetChanged();
        }

        setMessageContent("蓝牙连接成功");
    }

    @Override
    public void onLuckDisConnected() {
        setMessageContent("连接已断开");
        if (deviceAdapter != null) {
            deviceAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 测试数据
     */
    private void doTest() {
        PrinterHelper.getInstance().sendCommand(
                new byte[]{0x10, (byte) 0xff, (byte) 0x70},
                null
        );
//        BaseDevice device = PrinterHelper.getInstance().getPrinterDevice();
//        if (device instanceof BaseNormalDevice) {
//            BaseNormalDevice nDevice = (BaseNormalDevice) device;
//
//            nDevice.enablePrinterLuck();
//            nDevice.printerWakeupLuck();
//            nDevice.printLineDotsLuck(80);
//            nDevice.printLineDotsLuck(140);
//            nDevice.stopPrintJobLuck(new ResultCallback<Integer>() {
//                @Override
//                public void onSuccess(Integer data) {
//                    Log.d(TAG, "print end...");
//                }
//
//                @Override
//                public void onFail() {
//
//                }
//            });
//        }

    }

    private byte[] getAssetFileBytes(String path) {
        InputStream in = null;
        byte[] result = new byte[0];
        try {
            in = getAssets().open(path);
            byte[] buffer = new byte[3];
            while (true) {
                int readCount = in.read(buffer);
                if (readCount < 0) {
                    break;
                }
                byte item1 = getItemByteFromText(buffer[0]);
                byte item0 = getItemByteFromText(buffer[1]);
                byte combine = (byte) ((item1 << 4) | item0);
                byte[] newResult = new byte[result.length + 1];
                System.arraycopy(result, 0, newResult, 0, result.length);
                newResult[result.length] = combine;
                result = newResult;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private byte getItemByteFromText(byte c) {
        switch (c) {
            case '0':
                return 0x00;
            case '1':
                return 0x01;
            case '2':
                return 0x02;
            case '3':
                return 0x03;
            case '4':
                return 0x04;
            case '5':
                return 0x05;
            case '6':
                return 0x06;
            case '7':
                return 0x07;
            case '8':
                return 0x08;
            case '9':
                return 0x09;
            case 'a':
                return 0x0a;
            case 'b':
                return 0x0b;
            case 'c':
                return 0x0c;
            case 'd':
                return 0x0d;
            case 'e':
                return 0x0e;
            case 'f':
                return 0x0f;
        }
        return 0x00;
    }

    private void showStatusToast(int status) {
        switch (status) {
            case PrinterStatus.PRINTER_STATUS_OUTPAPER:
                PrinterUtil.showToast("缺纸");
                break;
            case PrinterStatus.PRINTER_STATUS_OPENCOVER:
                PrinterUtil.showToast("开盖");
                break;
            case PrinterStatus.PRINTER_STATUS_OVERHEAT:
                PrinterUtil.showToast("过热");
                break;
            case PrinterStatus.PRINTER_STATUS_LOWVAL:
                PrinterUtil.showToast("低电压");
                break;
            default:
                PrinterUtil.showToast("打印失败");
                break;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void setMessageContent(String message) {
        tv_message.post(() -> {
            tv_message.setText(message);
        });
    }

    @Override
    public void onLabelPaperError() {
        showToast("标签纸纸张放置错误");
    }
}
