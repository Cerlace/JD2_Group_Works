package itacademy.servlet;

import itacademy.dto.CarDTO;
import itacademy.service.CarService;
import itacademy.service.impl.CarServiceImpl;
import itacademy.utils.HibernateUtil;
import itacademy.utils.ServletConstants;
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
    private final CarService carService = new CarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = ServletUtil.getIntegerParam(req, ServletConstants.CAR_ID_PARAMETER);

        if (id == null) {
            resp.sendRedirect(req.getContextPath() + ServletConstants.ERROR_JSP);
            return;
        }

        CarDTO car = carService.get(id);
        req.setAttribute(ServletConstants.CAR_ATTRIBUTE, car);

        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher(ServletConstants.CARS_UPDATE_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        carService.update(
                ServletUtil.getIntegerParam(req, ServletConstants.CAR_ID_PARAMETER),
                ServletUtil.mapCar(req));

        resp.sendRedirect(ServletConstants.CARS_LIST_SERVLET);
    }

    @Override
    public void destroy() {
        this.carService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
