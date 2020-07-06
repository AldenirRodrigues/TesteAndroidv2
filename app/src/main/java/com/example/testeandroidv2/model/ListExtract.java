
package com.example.testeandroidv2.model;

import com.example.testeandroidv2.interfaces.ExtratoInterface;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillsToPay {

    @SerializedName("statementList")
    private List<StatementList> mStatementList;



    public List<StatementList> getStatementList() {
        return mStatementList;
    }

    public void setStatementList(List<StatementList> statementList) {
        mStatementList = statementList;
    }


}
