<!--
 * @Author: OriginalCoder
 * @Date: 2020-12-08 15:17:26
 * @version: 
 * @LastEditTime: 2020-12-08 18:26:35
 * @LastEditors: OriginalCoder
 * @Description: 
 * @Email: 1720929001@qq.com
 * @Other: 
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>管理员登录</title>
    <link rel="stylesheet" href="./css/login.css" />
  </head>
  <body>
    <div class="main" style="margin: 0 auto; width: 400px; height: 500px">
      <h1>欢迎登陆：</h1>
      <form action="/SSM/login" method="POST">
        <table>
          <tr>
            <td>登陆账号：</td>
            <td>
              <input type="text" name="uid" value="" />
            </td>
            <td>
              <span class="error" style="color: red; font-size: 18px"
                >${uidError}</span
              >
            </td>
          </tr>
          <tr>
            <td>登陆密码：</td>
            <td>
              <input type="password" name="pwd" value="" />
            </td>
            <td>
              <span class="error" style="color: red; font-size: 18px"
                >${pwdError}</span
              >
            </td>
          </tr>
          <tr>
            <td>
              <input type="submit" value="登录" />
            </td>
            <td>
              <input type="reset" value="重置" />
            </td>
          </tr>
        </table>
      </form>
    </div>
  </body>
</html>
