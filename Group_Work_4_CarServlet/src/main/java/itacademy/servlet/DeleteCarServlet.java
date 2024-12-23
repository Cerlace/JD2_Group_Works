package itacademy.servlet;

import itacademy.service.CarService;
import itacademy.service.impl.CarServiceImpl;
import itacademy.utils.HibernateUtil;
import itacademy.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteCarServlet", value = "/delete")
public class DeleteCarServlet extends HttpServlet {
    private final CarService carService = new CarServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = ServletUtil.getParam(req, "id");
        this.carService.delete(id);
        resp.sendRedirect("cars_manager");
    }

    @Override
    public void destroy() {
        this.carService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
