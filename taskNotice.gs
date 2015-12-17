function sendTask() {
  // APIキーを変更
  var client = ChatWorkClient.factory({token: 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'});
  
  // 送信先のroom_idを変更
  var room_id = XXXXXXXX
  // account_idを変更
  var account_id = XXXXXXXX
  var mytasks =  client.getMyTasks({
    account_id: account_id
  });
  
  for each (var task in mytasks){
    var current_time = new Date().getTime();
    var limit_time = task["limit_time"]*1000;
    if (current_time > limit_time && (limit_time != 0)){
      var body = task["body"];
      var d = new Date(limit_time)
      var msg = "[info][title]期限切れタスク[/title]";
      msg+ = "Due: " + (d.getMonth()+1) + "/" + d.getDate() + d.getYear() + "\n";
      msg += body;
      msg += "[/info]"
      
      client.sendMessage({room_id: room_id, body: msg});
    }
    
  }
}

