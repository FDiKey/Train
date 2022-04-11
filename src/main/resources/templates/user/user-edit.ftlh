<#import "../parts/common.ftlh" as c>

<@c.page>
    <span>${user.username}</span>
    <form method="post" action="${user.id}">
        <#list roles as role>
            <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</input>
        </#list>
        <input type="hidden" name="userId" value="${user.id}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Save</button>
    </form>
</@c.page>