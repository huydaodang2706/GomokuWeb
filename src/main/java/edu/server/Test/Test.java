package edu.server.Test;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Test{
    public static void main(String[] args) {
        String json ="{"
                + "\"Emp_Name\":\"Robert\""
                + ",\"Emp_Gen\":\"No1332\""
                + ",\"Emp_dept\":\"Developement\""
                + ",\"Emp_age\":\"25\""
                + ",\"Emp_Pos\":\"Staff\""
                + ",\"Emp_colleague\":[\"Lina\",\"Tom\",\"Thomas\"]"
                + "}";
        // Cách 1 chuyển đổi json thành object
        Gson gson = new Gson(); // khởi tạo Gson
        Employee employee = gson.fromJson(json, Employee.class); // parse Gson về object
        System.out.println("Emp_Name:"+ employee.emp_Name);
        System.out.println("Emp_Gen:"+ employee.emp_Gen);
        System.out.println("Emp_age:"+ employee.emp_age);
        System.out.println("Emp_Pos:"+ employee.emp_Pos);
        System.out.println("Emp_dept:"+ employee.emp_dept);
        System.out.println("Emp_colleague:"+ employee.emp_colleague.toString());
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