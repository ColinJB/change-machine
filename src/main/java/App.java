import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/result", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      ChangeMachine change = new ChangeMachine();
      String stringAmount = request.queryParams("amount");
      Float amount = Float.parseFloat(stringAmount);
      String result = change.makeChange(amount);
      model.put("amount", amount);
      model.put("result", result);
      model.put("template", "templates/result.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
