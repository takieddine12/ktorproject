package m.first.ktor.remote

data  class gamesModel(
    var HomeTeam : String ,
    var AwayTeam : String ,
    var HomeTeamId : Int ,
    var AwayTeamId : Int ,
    var HomeTeamLogo : String ,
    var AwayTeamLogo : String ,
    var Time : Int ,
    var Date : Int
)