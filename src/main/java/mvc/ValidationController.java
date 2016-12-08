package mvc;

import dto.OptionTradeProduct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.ProductService;
import services.ValidationService;

import java.util.Map;

/**
 * Controller for trade product json validation.
 */
@Controller
public class ValidationController {

    private final static String FORM = "validationForm";

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ProductService productService;

    /**
     * A controller method return main form to fill trade product information.
     *
     * @param model ModelMap.
     * @return main form.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String main(ModelMap model) {

        //We use reflection to achieve flexibility for extension product fields
        final Map supportedProductsMap = productService.getFieldsForSupportedProductsMap();
        model.addAttribute("supportedProductsMap", supportedProductsMap);
        model.addAttribute("supportedProducts", supportedProductsMap.keySet());
        return FORM;
    }

    /**
     * A controller method return result of validation.
     *
     * @param json string that is a body of request, the string will be validated.
     * @param product object of trade product.
     * @param bindingResult validation result.
     * @return validation result as json.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    //No need annotation @Valid here because we will set values later from json
    public String validate(@RequestBody String json, OptionTradeProduct product, BindingResult bindingResult) {

        final JSONObject validationResult = validationService.validate(json, product, bindingResult);

        //additional linkage between the error and the actual trade
        validationResult.put("trade", json);
        return validationResult.toString();
    }
}