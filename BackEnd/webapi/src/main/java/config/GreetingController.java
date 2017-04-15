package config;

import ds.frontend.domain.searchcategory.request.SearchCategoryRequest;
import ds.frontend.domain.searchcategory.request.SearchCategoryRequestWrapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value = "/searchCategory", method = RequestMethod.POST)
    public SearchCategoryRequest searchCategory(@RequestBody SearchCategoryRequestWrapper searchCategoryRequestWrapper, HttpServletRequest request, HttpServletResponse response) {
        printHeaders(request);

        SearchCategoryRequest category = searchCategoryRequestWrapper.getSearchCategoryRequest();
        category.setCategoryName(category.getCategoryName() + "-Changed");
        System.out.println(category);
        response.setHeader("Content-Type", "application/json");

        return category;
        /*SuccessResponse successResponse = new SuccessResponse();
        successResponse.setResult(new ArrayList<Result>() {{
            add(new Result(123L, "Forest category"));
        }});
        ResponseDetail responseDetail = new ResponseDetail(200L, "Success");
        SearchCategoryResponse searchCategoryResponse = new SearchCategoryResponse(responseDetail, successResponse, null);
        return new SearchCategoryResponseWrapper(searchCategoryResponse);*/
    }

    public void printHeaders(HttpServletRequest request) {
        Enumeration<String> headerNamesEnum = request.getHeaderNames();
        while (headerNamesEnum.hasMoreElements()) {
            String headerName = headerNamesEnum.nextElement();
            System.out.println(headerName + ":" + request.getHeader(headerName));
        }
    }

}