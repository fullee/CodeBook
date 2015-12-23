package DataBeans;

/**
 * Created by root on 2015/12/4.
 */
public class BooksInfo {
    private int Pic;
    private String title;
    private String bookurl;

    public String getBookurl() {
        return bookurl;
    }

    public void setBookurl(String bookurl) {
        this.bookurl = bookurl;
    }

    public int getPic() {
        return Pic;
    }

    public void setPic(int pic) {
        Pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
