package itacademy.servlet;

import itacademy.dto.CarDTO;
import itacademy.service.CarService;
import itacademy.service.impl.CarServiceImpl;
import itacademy.utils.HibernateUtil;
import itacademy.utils.ServletConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "listCarsServlet", value = "/cars_manager")
public class ListCarsServlet extends HttpServlet {
    private final CarService carService = new CarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<CarDTO> carDTOList = this.carService.getAll();

        req.setAttribute(ServletConstants.CARS_LIST_ATTRIBUTE, carDTOList);

        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher(ServletConstants.CARS_LIST_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        this.carService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
