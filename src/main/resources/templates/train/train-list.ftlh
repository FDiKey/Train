<#import "../parts/common.ftlh" as c>

<@c.page>

    <#list trains as train>
        ${train.trainNumber}
        ${train.dateStart}
        ${train.timeStart}
        ${train.route.number}

        <a href="./train-edit/${train.id}">edit</a>
        <a href="./train-edit/${train.id}">delete</a>
        <br>
    </#list>
    <a href="http://localhost:8080/admin/train-add" class="btn btn-primary"> Add new train </a>
</@c.page>