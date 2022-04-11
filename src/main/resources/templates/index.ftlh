<#import "parts/common.ftlh" as c>
<@c.page>

    <form action="" method="get">
        <input type="text" name="stationFrom" placeholder="From">
<#--        <select name="stationFrom">-->
<#--            <#list stationByRoute as k, v>-->
<#--                <#list v as station>-->
<#--                <option>${station.name}</option>-->
<#--                </#list>-->
<#--            </#list>-->
<#--        </select>-->

        <input type="text" name="stationTo" placeholder="To">
<#--        <select name="stationTo">-->
<#--            <#list stationByRoute as k, v>-->
<#--                <#list v as station>-->
<#--                    <option>${station.name}</option>-->
<#--                </#list>-->
<#--            </#list>-->
        </select>

        <button type="submit">Search</button>
    </form>
    <div class="mt-3">
        ${message?if_exists}
        <#if schedulesFrom??>
            <#list schedulesFrom as scheduleFrom>
                <#list schedulesTo as scheduleTo>
                    <#if scheduleFrom.trainNumber = scheduleTo.trainNumber>

                    <div class="card mb-3">
                        <div class="card-header">
                            Train №: ${scheduleFrom.trainNumber}
                        </div>
                        <form action="ticket/ticket-buy"  method="get">
                        <div class="card-body">
                            <div class="container">
                                <div class="row g-3">
                                    <div class="col-4 ml-5" >
                                        Station from: <H3>${scheduleFrom.stationName}</H3>
                                        <span>On station</span>
                                        : ${scheduleFrom.dateOnStation}
                                        at time : ${scheduleFrom.timeOnStation}
                                        <p class="card-text">Count of free seats: ${scheduleFrom.trainSeatCount}</p>
                                    </div>
                                    <div class="col-4 ml-5" >
                                        Station to: <H3>${scheduleTo.stationName}</H3>
                                        <span>On station</span>
                                        : ${scheduleTo.dateOnStation}
                                        at time : ${scheduleTo.timeOnStation}
                                        <p class="card-text">Count of free seats: ${scheduleTo.trainSeatCount}</p>
                                    </div>
                                    <div class="col-2 mt-4">
<#--                                      <button type="submit" class="btn btn-primary">Buy ticket</button>-->
                                        <a href="ticket-buy/${scheduleFrom.id}/${scheduleTo.id}" class="btn btn-primary">Buy the ticket</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </form>
                    </div>
                    </#if>
                </#list>
            <#else>
                Choose your train!
            </#list>
        </#if>
    </div>
</@c.page>