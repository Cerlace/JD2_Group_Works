package itacademy.servlet;

import itacademy.dto.CarDTO;
import itacademy.service.CarService;
import itacademy.service.impl.CarServiceImpl;
import itacademy.utils.HibernateUtil;
import itacademy.utils.ServletUtil;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = ServletUtil.getParam(req, "id");
        String vin = ServletUtil.getParam(req, "vin");
        String name = ServletUtil.getParam(req, "name");

        carService.update(id, CarDTO.builder()
                .name(name)
                .vin(vin)
                .build());

        resp.sendRedirect("cars_manager");
    }

    @Override
    public void destroy() {
        this.carService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
