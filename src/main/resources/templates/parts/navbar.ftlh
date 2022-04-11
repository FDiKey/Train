<#import "login.ftlh" as l>
<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
    <a class="navbar-brand" href="/">Railways</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
            </li>
            <#if isAdmin>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="http://localhost:8080/admin" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Admin
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="admin/station-list">Stations</a></li>
                        <li><a class="dropdown-item" href="admin/route-list">Routes</a></li>
                        <li><a class="dropdown-item" href="admin/train-list">Trains</a></li>
                        <li><a class="dropdown-item" href="#">Refresh schedule</a></li>
                    </ul>
                </li>
            </#if>
            <#if known>
            <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/my-tickets">My tickets</a>
            </li>
            </#if>
        </ul>
        <#if username??>
            <div class="member-name-link mr-3">
                <a href="http://localhost:8080/profile" class="navbar-text">${username}</a>
            </div>
        <#else>
            <div class="navar-text">${username}</div>
        </#if>
        <#if known>
            <@l.logout/>
        <#else>
            <form class="d-flex mr-3" action="login">
                <button class="btn btn-outline-success" type="submit">Sign in</button>
            </form>
        </#if>

    </div>
    </div>
</nav>