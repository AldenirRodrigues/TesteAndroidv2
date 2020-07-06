
package com.example.testeandroidv2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListExtract {

    @SerializedName("statementList")
    private List<StatementList> mStatementList;



    public List<StatementList> getStatementList() {
        return mStatementList;
    }

    public void setStatementList(List<StatementList> statementList) {
        mStatementList = statementList;
    }


}
