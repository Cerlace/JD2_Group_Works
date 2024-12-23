package itacademy.servlet;

import itacademy.dto.CarDTO;
import itacademy.service.CarService;
import itacademy.service.impl.CarServiceImpl;
import itacademy.utils.HibernateUtil;
import itacademy.utils.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "saveCarServlet", value = "/save")
public class SaveCarServlet extends HttpServlet {
    private static final String RESULT_MESSAGE_ATTRIBUTE = "resultMessage";
    private static final String SAVE_JSP = "/save.jsp";
    private final CarService carService = new CarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher(SAVE_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vin = ServletUtil.getParam(req, "vin");
        String name = ServletUtil.getParam(req, "name");

        CarDTO carDTO = carService.save(CarDTO.builder()
                .name(name)
                .vin(vin)
                .build());

        req.setAttribute(RESULT_MESSAGE_ATTRIBUTE, carDTO);

        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        this.carService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
