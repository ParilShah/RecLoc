package controllers;

import ds.frontend.domain.category.Category;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by desair4 on 4/13/2017.
 */
@RestController
public class CategoryController {

    @RequestMapping(value = "/submitCategory", method = RequestMethod.POST)
    Category submitCategory(@RequestBody Category category) {

        System.out.println(category);
        return null;
    }
}
