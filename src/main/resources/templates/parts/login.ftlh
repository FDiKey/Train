<#macro login path>
<#--    -->
<#--    <form action="${path}" method="post">-->
<#--        <div><label> E-mail : <input type="email" name="username"/> </label></div>-->
<#--        <div><label> Password: <input type="password" name="password"/> </label></div>-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--        <#if path="/registration">-->
<#--            <div><label> Repeate password: <input type="password" name="password2"/> </label></div>-->
<#--            <div><input type="submit" value="Sign up"/></div>-->
<#--        <#else>-->
<#--            <div><input type="submit" value="Sign In"/></div>-->
<#--        </#if>-->


<#--    </form>-->


    <form action="${path}" method="post">
        <div class="row mb-3 ml-5">
            <label for="username" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-5">
                <input type="email" class="form-control l-3" id="username" name="username" placeholder="ivan@ivan.com">
            </div>
        </div>
        <div class="row mb-3 ml-5">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
        </div>
        <#--        password 2 for registration-->
        <#if path="/registration">
            <div class="row mb-3 ml-5">
                <label for="password2" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" id="password2" name="password2">
                </div>
            </div>
        </#if>
        <#--        csrf-->
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="row ml-100">
            <div class="col-sm-5 ml-10">
<#--                if form login show two buttons sign up and sign in-->
                <#if path="/login">
                    <button type="submit" class="btn btn-success ml-10">Sign in</button>
                    <a href="registration" class="btn btn-danger ml-10">Sign up</a>
                <#else>
<#--                    else show only one button-->
                    <button type="submit" class="btn btn-success ml-10">Sign up</button>
                    <a href="login" class="btn btn-primary ml-10">Back</a>
                </#if>
            </div>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-danger">Sign Out</button>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
    </form>
</#macro>