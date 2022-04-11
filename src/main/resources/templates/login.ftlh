<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <@l.login "/login"/>
    <a href="/registration"> sign up </a>
</@c.page>