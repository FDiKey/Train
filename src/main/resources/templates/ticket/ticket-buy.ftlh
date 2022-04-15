<#import "../parts/common.ftlh" as c>
<@c.page>

    <div class="card mb-3">
        <div class="card-header">
            Train №: ${scheduleFrom.trainNumber}
        </div>
            <div class="card-body">
                <div class="container">
                    <div class="row g-3">
                        <div class="col-5 ml-5" >
                            Station from: <H3>${scheduleFrom.stationName}</H3>
                            <span>On station</span>
                            : ${scheduleFrom.date}
                            at time : ${scheduleFrom.time}
                            <p class="card-text">Count of free seats: ${scheduleFrom.seatCount}</p>
                        </div>
                        <div class="col-5 ml-5" >
                            Station to: <H3>${scheduleTo.stationName}</H3>
                            <span>On station</span>
                            : ${scheduleTo.date}
                            at time : ${scheduleTo.time}
                            <p class="card-text">Count of free seats: ${scheduleTo.seatCount}</p>
                        </div>
                        <div class="col-2 mt-4">
                        </div>
                    </div>
                </div>
            </div>
    </div>
    <div class="row g-4">
    <#list seatCount as seat>
    <form method="post" action="/ticket-buy/${scheduleFrom.id}/${scheduleTo.id}/${seat}">
            <div class="col-sm-3 ml-5 mb-3">
            <div class="card" style="width: 18rem;">
                <div class="card-header">
                    Price : ${price}
                </div>
                <div class="card-body">
                    <h1 class="card-Text col-second" name="seat${seat}">${seat}</h1>
                    <button type="submit" values="buy" class="btn btn-primary ml-5">Buy</button>
                </div>
            </div>
            </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
    </#list>
    </div>
</@c.page>