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

@WebServlet(name = "updateCarServlet", value = "/update")
public class UpdateCarServlet extends HttpServlet {
    private static final String RESULT_MESSAGE_ATTRIBUTE = "resultMessage";
    private static final String UPDATE_JSP = "/update.jsp";
    private static final String CAR_NOT_FOUND = "Car not found";
    private final CarService carService = new CarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher(UPDATE_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = ServletUtil.getParam(req, "id");
        String vin = ServletUtil.getParam(req, "vin");
        String name = ServletUtil.getParam(req, "name");

        CarDTO carDTO = carService.update(id, CarDTO.builder()
                .name(name)
                .vin(vin)
                .build());

        String resultMessage = (carDTO.getId() != null) ?
                carDTO.toString() : CAR_NOT_FOUND;
        req.setAttribute(RESULT_MESSAGE_ATTRIBUTE, resultMessage);

        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        this.carService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}