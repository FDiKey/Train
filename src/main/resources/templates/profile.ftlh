<#import "parts/common.ftlh" as c>
    <#import "parts/login.ftlh" as l>
<@c.page>
    ${message?if_exists}
    <form method="post">
        <input type="email" name="email" value="${email}"/>
        <hr>
        <input type="password" name="password" placeholder="Password"/>
        <input type="password" name="password2" placeholder="Repeate Password"/>
        <hr>
        <br>
        <input type="text" name="name" value="${name}" placeholder="Name"/>
        <input type="text" name="surname" value="${surname}" placeholder="Surname"/>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <button type="submit">Save</button>
        <button type="reset">Reset</button>
    </form>
</@c.page>