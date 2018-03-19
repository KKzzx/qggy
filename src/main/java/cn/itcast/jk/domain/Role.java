package cn.itcast.jk.domain;

import java.io.Serializable;

/**
 * 类别
 *
 * @author Administrator
 */
public class Role implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6492945000584070385L;
    private String id; // 将主键都映射成id
    private String roleName;
    private String roleRemark;
    private String roleState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark;
    }

    public String getRoleState() {
        return roleState;
    }

    public void setRoleState(String roleState) {
        this.roleState = roleState;
    }

}
