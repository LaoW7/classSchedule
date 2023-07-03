package cn.edu.hzcu.ky.dao;

import java.sql.Connection;
import cn.edu.hzcu.ky.model.BeanAdministrator;


public class AdministratorDao {//管理员登录
    public BeanAdministrator login(Connection conn, BeanAdministrator administrator) throws Exception {
        BeanAdministrator result = null;
        String sql = "select * from administrator where AdministratorID=? and LoginPassword=?";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, administrator.getAdministratorID());
        pst.setString(2, administrator.getLoginPassword());
        java.sql.ResultSet rs = pst.executeQuery();
        if(rs.next()){
            result = new BeanAdministrator();
            result.setAdministratorID(rs.getString(1));
            result.setName(rs.getString(2));
            result.setLoginPassword(rs.getString(3));
        }

        return result;
    }
}
