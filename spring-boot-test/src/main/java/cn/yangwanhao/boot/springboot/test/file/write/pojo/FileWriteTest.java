package cn.yangwanhao.boot.springboot.test.file.write.pojo;

import java.io.Serializable;

public class FileWriteTest implements Serializable {
    private String id;

    private String name;

    private String phone;

    private String email;

    private String address;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(id).append("|").append(name).append("|").append(phone).append("|").append(email).append("|").append(address);
        return sb.toString();
    }
}