package com.luckprinter.demo.test;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;

/**
 * @author HuangXiaohui
 * @date 2025-03-20 23:05
 */
public class BleTest {
    private static BleDevice bleDevice = null;
    public static void connectBle(String mac) {
        BleManager.getInstance().connect(mac, new BleGattCallback() {
            @Override
            public void onStartConnect() {
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                log("connect fail");
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onConnectSuccess(BleDevice b, final BluetoothGatt gatt, int status) {
                log("connect success");
                bleDevice = b;
                Handler hd = new Handler(Looper.getMainLooper());
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BluetoothGattService myService = null;
                        for (BluetoothGattService service : gatt.getServices()) {
                            if (service.getUuid().toString().startsWith("0000ff00")) {
                                myService = service;
                                break;
                            }
                        }

                        if (myService != null) {
                            for (BluetoothGattCharacteristic characteristic : myService.getCharacteristics()) {
                                String cUuid = characteristic.getUuid().toString();
                                //write
                                if (cUuid.startsWith("0000ff02")) {
                                    doWrite(characteristic);
                                } else if (cUuid.startsWith("0000ff01")) { // receive
                                    doNotifyReceive(characteristic);
                                } else if (cUuid.startsWith("0000ff03")) { // mtu credit
                                    doNotifyMtuCredit(characteristic);
                                }
                            }
                        }
                    }
                }, 2000);


            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            private void doNotifyReceive(final BluetoothGattCharacteristic characteristic) {
                Handler hd = new Handler(Looper.getMainLooper());
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BleManager.getInstance().notify(
                                bleDevice,
                                characteristic.getService().getUuid().toString(),
                                characteristic.getUuid().toString(),
                                new BleNotifyCallback() {

                                    @Override
                                    public void onNotifySuccess() {
                                        log("notifyReceive success");
                                    }

                                    @Override
                                    public void onNotifyFailure(final BleException exception) {
                                        log("notifyReceive fail:" + exception.getDescription());
                                    }

                                    @Override
                                    public void onCharacteristicChanged(byte[] data) {
                                        log("notify receive : " + HexUtil.formatHexString(characteristic.getValue(), true));
                                    }
                                });
                    }
                }, 2000);
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            private void doNotifyMtuCredit(final BluetoothGattCharacteristic characteristic) {
                Handler hd = new Handler(Looper.getMainLooper());
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BleManager.getInstance().notify(
                                bleDevice,
                                characteristic.getService().getUuid().toString(),
                                characteristic.getUuid().toString(),
                                new BleNotifyCallback() {

                                    @Override
                                    public void onNotifySuccess() {
                                        log("notifyMtuCredit success");
                                    }

                                    @Override
                                    public void onNotifyFailure(final BleException exception) {
                                        log("notifyMtuCredit fail:" + exception.getDescription());
                                    }

                                    @Override
                                    public void onCharacteristicChanged(byte[] data) {
                                        log("notifyMtuCredit receive : " + HexUtil.formatHexString(characteristic.getValue(), true));
                                    }
                                });
                    }
                }, 4000);

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            private void doWrite(final BluetoothGattCharacteristic characteristic) {
                Handler hd = new Handler(Looper.getMainLooper());
                long baseTime = 5000;
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doWriteItem(characteristic, "10ff20f0");
                    }
                }, baseTime + 2000);
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doWriteItem(characteristic, "10ff20f0");
                    }
                }, baseTime + 4000);
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doWriteItem(characteristic, "10ff20f0");
                    }
                }, baseTime + 6000);
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doWriteItem(characteristic, "10ff20f0");
                    }
                }, baseTime + 8000);
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doWriteItem(characteristic, "10ff20f0");
                    }
                }, baseTime + 10_000);
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BleManager.getInstance().disconnect(bleDevice);
                        log("disconnect");
                    }
                }, baseTime + 12_000);
            }


            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            private void doWriteItem(BluetoothGattCharacteristic characteristic, String hex) {
                BleManager.getInstance().write(
                        bleDevice,
                        characteristic.getService().getUuid().toString(),
                        characteristic.getUuid().toString(),
                        HexUtil.hexStringToBytes(hex),
                        new BleWriteCallback() {

                            @Override
                            public void onWriteSuccess(final int current, final int total, final byte[] justWrite) {
                                log("write success:" + HexUtil.formatHexString(justWrite, true));
                            }

                            @Override
                            public void onWriteFailure(final BleException exception) {
                                log("write fail:" + exception.toString());
                            }
                        });
            }



            private void log(String msg) {
                Log.i("MMMM", " >>> " + msg);
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice bleDevice, BluetoothGatt gatt, int status) {

            }
        });
    }
}
