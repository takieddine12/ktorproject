package m.first.ktor

import com.fasterxml.jackson.databind.SerializationFeature
import com.sun.xml.internal.ws.client.ContentNegotiation
import io.ktor.application.*
import io.ktor.features.StatusPages
import io.ktor.jackson.jackson
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import m.first.ktor.remote.gamesModel
import m.first.ktor.remote.wrapGamesModel
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty,port){
        install(StatusPages){
            exception<Throwable> { error ->
                print("Exception Occured ${error.localizedMessage}")
            }
        }

        install(io.ktor.features.ContentNegotiation){
          jackson {
              enable(SerializationFeature.INDENT_OUTPUT)
          }
        }
        routing {
            get("/games"){
              //Set Up Data
                val calendar  = Calendar.getInstance()
                val Time = calendar.get(Calendar.HOUR_OF_DAY)
                var Date = calendar.get(Calendar.DATE)

                var gamesList = getGamesDetails(Time,Date)
                var models = wrapGamesModel(gamesList)
                call.respond(models)


            }


        }

    }.start(wait = true)

}

public fun getGamesDetails(Time : Int , Date : Int) : MutableList<gamesModel>{
    var gamesList = mutableListOf<gamesModel>()
    var gamesModel = gamesModel("Algeria","England",1200245,
        1200256,"https://cdn.pixabay.com/photo/2014/04/03/00/43/lion-309219_960_720.png",
        "https://cdn.pixabay.com/photo/2014/04/03/00/43/wildcat-309221_960_720.png",Time,Date)
    var gamesModel1 = gamesModel("France","Croatia",1200278,
        1200220,"https://cdn.pixabay.com/photo/2014/04/03/00/43/lion-309219_960_720.png",
        "https://cdn.pixabay.com/photo/2014/04/03/00/43/wildcat-309221_960_720.png",Time,Date)
    var gamesModel2 = gamesModel("India","Italy",1200205,
        1200201,"https://cdn.pixabay.com/photo/2014/04/03/00/43/lion-309219_960_720.png",
        "https://cdn.pixabay.com/photo/2014/04/03/00/43/wildcat-309221_960_720.png",Time,Date)
    var gamesModel3 = gamesModel("Tunisia","Norway",1200175,
        1200150,"https://cdn.pixabay.com/photo/2014/04/03/00/43/lion-309219_960_720.png",
        "https://cdn.pixabay.com/photo/2014/04/03/00/43/wildcat-309221_960_720.png",Time,Date)

    gamesList.add(gamesModel)
    gamesList.add(gamesModel1)
    gamesList.add(gamesModel2)
    gamesList.add(gamesModel3)

    return gamesList



}