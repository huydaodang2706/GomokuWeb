package edu.server.test;

import com.google.gson.Gson;
import edu.common.packet.client.RuleSet;
import edu.common.packet.server.GameInfo;

public class TestPacket {
    public static void sendObject(Object p){
        Gson gson = new Gson();
        String a = gson.toJson(p).toString();
        System.out.println(a);
    }

    public static void main(String[] args) {

        GameInfo info = new GameInfo(new RuleSet(10,20,30),new String("Thanh"));
        Gson gson = new Gson(); // khởi tạo Gson
        String data = gson.toJson(info).toString(); // parse Gson về object
        System.out.println(data);

        GameInfo a = gson.fromJson(data,GameInfo.class);
        System.out.println(a.getRuleSet().getSize());
//        // cách 2: không chuyển đổi json thành object mà parse trực tiếp
//       JsonObject jsonObject =JsonParser.parseString(json).getAsJsonObject();// Nếu bạn không muốn chuyển thành object có thể sử dụng
//       JsonArray jsonArray = (JsonArray) jsonObject.get("Emp_colleague");
//       System.out.println("Emp_Name:"+ jsonObject.get("Emp_Name"));
//       System.out.println("Emp_Gen:"+ jsonObject.get("Emp_Gen"));
//       System.out.println("Emp_age:"+ jsonObject.get("Emp_age"));
//       System.out.println("Emp_Pos:"+ jsonObject.get("Emp_Pos"));
//       System.out.println("Emp_dept:"+ jsonObject.get("Emp_dept"));
//       System.out.println("Emp_colleague:"+ jsonObject.get("Emp_colleague").toString());

    }
}
