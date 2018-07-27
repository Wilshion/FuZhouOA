package com.wilshion.oa.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Wilshion on 2018/7/27 08:46.
 * [description : ]
 * [version : 1.0]
 */
public class PersonListRespBean {
    private List<PersonBean> personList;

    public List<PersonBean> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonBean> personList) {
        this.personList = personList;
    }

    public static class PersonBean implements Parcelable {

        /**
         * SEQ_ID : 1
         * USER_NAME : admin
         */

        private int SEQ_ID;
        private String USER_NAME;

        /**
         * 扩展参数，用作人员选择
         */
        public boolean isSelected;

        public int getSEQ_ID() {
            return SEQ_ID;
        }

        public void setSEQ_ID(int SEQ_ID) {
            this.SEQ_ID = SEQ_ID;
        }

        public String getUSER_NAME() {
            return USER_NAME;
        }

        public void setUSER_NAME(String USER_NAME) {
            this.USER_NAME = USER_NAME;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.SEQ_ID);
            dest.writeString(this.USER_NAME);
        }

        public PersonBean() {
        }

        protected PersonBean(Parcel in) {
            this.SEQ_ID = in.readInt();
            this.USER_NAME = in.readString();
        }

        public static final Parcelable.Creator<PersonBean> CREATOR = new Parcelable.Creator<PersonBean>() {
            @Override
            public PersonBean createFromParcel(Parcel source) {
                return new PersonBean(source);
            }

            @Override
            public PersonBean[] newArray(int size) {
                return new PersonBean[size];
            }
        };
    }
}
