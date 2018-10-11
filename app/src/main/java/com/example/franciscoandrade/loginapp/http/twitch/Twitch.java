package com.example.franciscoandrade.loginapp.http.twitch;

import java.util.List;

public class Twitch {
    private List<Data> data;

    private Pagination pagination;

    public List<Data> getData ()
    {
        return data;
    }

    public void setData (List<Data> data)
    {
        this.data = data;
    }

    public Pagination getPagination ()
    {
        return pagination;
    }

    public void setPagination (Pagination pagination)
    {
        this.pagination = pagination;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", pagination = "+pagination+"]";
    }
}
