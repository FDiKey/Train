<#import "../parts/common.ftlh" as c>
<@c.page>
    <#list routes as route>
        <div>${route.number}
        <a href="../route-edit/${route.id}">edit</a>
        <a href="../route-delete/">delete</a>
        </div>
    </#list>
</@c.page>