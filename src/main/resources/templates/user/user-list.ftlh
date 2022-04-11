<#import "../parts/common.ftlh" as c>
<@c.page>
    List of users
    <div>
    <table>
        <thead>
            <tr>Name </tr>
            <tr>Role </tr>
            <tr></tr>
        </thead>
        <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#sep> </#list></td>
            <td><a href="./user-edit/${user.id}">edit</a></td>
        </tr>
    </#list>
        </tbody>
    </table>
    </div>
</@c.page>