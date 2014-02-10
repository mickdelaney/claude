import org.scalatra.ScalatraServlet
import org.influxdb.scala._

package claude {
  class UpdateServlet extends ScalatraServlet {

    val db_host = System.getenv("CLAUDE_INFLUXDB_1_PORT_8086_TCP_ADDR")
    val db_port = System.getenv("CLAUDE_INFLUXDB_1_PORT_8086_TCP_PORT")
    val db = new InfluxDB(db_host, db_port, "root", "root", "weather") with StandaloneConfig

    post("/") {
      db.insertData("weather", Map(
        "time"                   -> parseDate(params("dateutc")),
        "temperature"            -> farenheit2celsius(params("tempf")),
        "wind_speed"             -> mph2kph(params("windspeedmph")),
        "atmmnospheric_pressure" -> inHg2Pascals(params("baromin")),
        "humidity"               -> percent2Decimal(params("humidity")),
        "winddir"                -> params("winddir"),
        "dew_point"              -> farenheit2celsius(params("deptf")),
        "wind_gust_speed"        -> mph2kph(params("windgustmph")),
        "daily_rainfall"         -> inches2millis("dailyrainin")
      ), SECONDS)
    }

    private def farenheit2celsius(f : Double) = (f - 32) * 5/9;
    private def inches2millis(i : Double) = i * 25.4;
    private def inHg2Pascals(i : Double) = i * 3386.38816;
  }

}
