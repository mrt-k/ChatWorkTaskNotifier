
import scalaj.http._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object gettask {
  def main(args: Array[String]) {
    // APIキーを変更
    val API_KEY = ""
    // room_idを変更
    val ROOM_ID = ""

    // 未完了のタスクを取得
    val response: HttpResponse[String] = Http("https://api.chatwork.com/v1/my/tasks?status=open").header("X-ChatWorkToken", API_KEY).asString
    // jsonのパース
    val jp = parse(response.body)
    // 現在取得をエポックタイムで取得
    val current_time: Long = System.currentTimeMillis / 1000
    // jsonから値を取り出す
    for {
      JArray(array) <- jp
      JObject(field) <- array
      JField("body", JString(body)) <- field
      JField("limit_time", JInt(limit_time)) <- field
    } yield {
      // 期限を過ぎていて, かつ, 期限が設定されていなければroomにpost
      if(current_time > limit_time && limit_time != 0) {
        // エポックタイムを変換
        val dt = new DateTime(limit_time.longValue*1000)
        val limit = dt.toString(DateTimeFormat.fullDate())
        val message = """
[info][title]期限切れタスク[/title]Due: %s
%s[/info]""".format(limit, body)
        val res =Http("https://api.chatwork.com/v1/rooms/" + ROOM_ID + "/messages").postForm(Seq("body" -> message)).header("X-ChatWorkToken", API_KEY).asString
      }
    }



  }
}
