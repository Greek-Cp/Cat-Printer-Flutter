package com.luckprinter.demo;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luckjingle.printersdk.R;
import com.luckprinter.sdk_new.device.BaseDevice;
import com.luckprinter.sdk_new.device.PrinterHelper;

import java.util.ArrayList;
import java.util.List;

public class PrinterDeviceAdapter extends RecyclerView.Adapter {
    public static final String  TAG = "PrinterDeviceAdapter";
    private List<DeviceItem> deviceList = new ArrayList<>();
    private ItemClickListener itemClickListener;

    public PrinterDeviceAdapter(List<DeviceItem> deviceList) {
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_printer_device, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Holder hd = (Holder) holder;
        DeviceItem item = deviceList.get(position);
        hd.setContent(item);

        hd.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        public void onItemClick(DeviceItem device);
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView tv_name, tv_connected;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_connected = itemView.findViewById(R.id.tv_connected);
        }

        public void setContent(DeviceItem item) {
            String name = item.getName();
            StringBuilder sb = new StringBuilder();
            sb.append(name);
//            sb.append("[").append(formatDeviceType(item.getType())).append("]");
            tv_name.setText(sb);

            int pos = getBindingAdapterPosition();
            BaseDevice device = PrinterHelper.getInstance().getPrinterDevice();
            String deviceName = null;
            if (device != null) {
                deviceName = device.getDeviceName();;
            }
            boolean isDeviceEquals = name != null && name.equals(deviceName);
            boolean isConnected = PrinterHelper.getInstance().isConnectedLuck();
            Log.d(TAG, "pos:" + pos + ", isDeviceEquals:" + isDeviceEquals + " isConnected:" + isConnected);

            if(isDeviceEquals && isConnected) {
                tv_connected.setVisibility(View.VISIBLE);
            }else{
                tv_connected.setVisibility(View.INVISIBLE);
            }
        }

        private String formatDeviceType(int type) {
            if (type == BluetoothDevice.DEVICE_TYPE_CLASSIC) {
                return "经典蓝牙";
            } else if (type == BluetoothDevice.DEVICE_TYPE_LE) {
                return "低功耗蓝牙";
            } else if (type == BluetoothDevice.DEVICE_TYPE_DUAL) {
                return "双模蓝牙";
            } else {
                return "?";
            }
        }
    }
}
