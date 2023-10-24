package learn.jpa.demo.security;

public interface Authority {
    public boolean isRead();
    public boolean isUpdate();
    public boolean isDelete();

    public void setRead(boolean permit);
    public void setUpdate(boolean permit);
    public void setDelete(boolean permit);

}
