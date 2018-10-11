package com.example.franciscoandrade.loginapp.http.twitch;

public class Pagination {
    private String cursor;

    public String getCursor ()
    {
        return cursor;
    }

    public void setCursor (String cursor)
    {
        this.cursor = cursor;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cursor = "+cursor+"]";
    }
}
