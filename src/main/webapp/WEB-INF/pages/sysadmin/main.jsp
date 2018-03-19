<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>模块介绍</title>
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/main.css" media="all"/>
</head>

<body>
<form>
    <div class="textbox"></div>

    <div class="modelDiv">

        <table class="modelTable" cellspacing="1">
            <tr>
                <td colspan="2" class="modelTitle">系统维护模块介绍</td>
            </tr>
            <tr>
                <td colspan="2" class="subModelTitle">系统管理</td>
            </tr>
            <tr>
                <td class="model_intro_left">多维权限控制模型：</td>
                <td class="model_intro_right">横向模块权限控制(菜单、按钮、状态、功能点), 纵向数据权限控制(课程维护人员、区域审核人员、总部审核人员)。
                    <p>＝严密的数据访问控制,充分保障数据的安全性。</p>
                </td>
            </tr>
            <tr>
                <td class="model_intro_left">角色管理：</td>
                <td class="model_intro_right">对人员角色进行权限分配, 实现用户权限批量设置, 支持细粒度权限控制分配(菜单、按钮、状态、功能点)。</td>
            </tr>
            <tr>
                <td class="model_intro_left" width="169">会员管理：</td>
                <td class="model_intro_right" width="81%">对注册会员进行管理。</td>
            </tr>
            <tr>
                <td class="model_intro_left" width="169">管理员管理：</td>
                <td class="model_intro_right" width="81%">可对系统操作用户进行维护管理, 维护信息包括用户账号、功能权限等, 并支持对特殊用户账号锁定禁用操作。

                </td>
            </tr>
            <tr>
                <td class="model_intro_left" width="169">区域管理：</td>
                <td class="model_intro_right" width="81%">可对系统区域进行维护管理。

                </td>
            </tr>
            <tr>
                <td class="model_intro_left" width="169">课程类别管理：</td>
                <td class="model_intro_right" width="81%">可对系统课程类别进行维护管理, 例如301、801。

                </td>
            </tr>
            <tr>
                <td class="model_intro_left" width="169">课程等级管理：</td>
                <td class="model_intro_right" width="81%">可对系统课程等级进行管理，例如初级、中级、高级。

                </td>
            </tr>
            <tfoot>
            <tr>
                <td colspan="2" class="tableFooter"></td>
            </tr>
            </tfoot>
        </table>

    </div>
</form>
</body>

</html>