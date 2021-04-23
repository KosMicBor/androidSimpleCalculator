package gu_android_1089.simplecalculator.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CalcDataSaver implements Parcelable {
    private double arg1;
    private double arg2;
    private double result;
    private ArrayList<String> argList;

    public void setArg1(double arg1) {
        this.arg1 = arg1;
    }

    public void setArg2(double arg2) {
        this.arg2 = arg2;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setArgList(ArrayList<String> argList) {
        this.argList = argList;
    }

    public CalcDataSaver(double arg1, double arg2, double result, ArrayList<String> argList) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
        this.argList = argList;
    }


    protected CalcDataSaver(Parcel in) {
        arg1 = in.readDouble();
        arg2 = in.readDouble();
        result = in.readDouble();
        argList = in.createStringArrayList();
    }

    public static final Creator<CalcDataSaver> CREATOR = new Creator<CalcDataSaver>() {
        @Override
        public CalcDataSaver createFromParcel(Parcel in) {
            return new CalcDataSaver(in);
        }

        @Override
        public CalcDataSaver[] newArray(int size) {
            return new CalcDataSaver[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(arg1);
        dest.writeDouble(arg2);
        dest.writeDouble(result);
        dest.writeStringList(argList);
    }

    public double getArg1() {
        return arg1;
    }

    public double getArg2() {
        return arg2;
    }

    public double getResult() {
        return result;
    }

    public ArrayList<String> getArgList() {
        return argList;
    }
}
