package itacademy.servlet;

import itacademy.dto.CarDTO;
import itacademy.service.CarService;
import itacademy.service.impl.CarServiceImpl;
import itacademy.utils.HibernateUtil;

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
    public static final String CARS = "cars";
    public static final String LIST_JSP = "/cars_manager.jsp";
    private final CarService carService = new CarServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterValues("ids");
        for (String id : ids) {
            this.carService.delete(id);
        }
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<CarDTO> carDTOList = this.carService.getAll();

        req.setAttribute(CARS, carDTOList);

        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher(LIST_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        this.carService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
