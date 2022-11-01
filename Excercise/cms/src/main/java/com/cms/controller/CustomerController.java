package com.cms.controller;



import com.cms.common.Alert;
import com.cms.model.CustomerEntity;
import com.cms.model.ProvinceEntity;
import com.cms.service.customer.ICustomerService;
import com.cms.service.province.IProvinceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import java.util.Optional;

@Controller
@SessionAttributes("customers")
public class CustomerController {

    private static Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("province")
    public Iterable<ProvinceEntity> province() {
        return provinceService.findAll();
    }

    @RequestMapping(value = "/customer/overview", method = RequestMethod.GET)
    public ModelAndView loadListCustomer(Pageable pageable, @RequestParam("s") Optional<String> s) {
        logger.trace("Go into loadListCustomer()");
        ModelAndView lisCustomer = null;
//        Iterable<CustomerEntity> customers;
        Page<CustomerEntity> customers;
        try {
            if (s.isPresent()) {
                customers = customerService.findAllByFirstNameContaining(s.get(), pageable);
                logger.info(customers.toString());
            } else {
                customers = customerService.findAll(pageable);
                logger.info(customers.toString());
            }
            lisCustomer = new ModelAndView("/customer/list");
            lisCustomer.addObject("customers", customers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit loadListCustomer()");
        return lisCustomer;
    }

    //load create form
    @RequestMapping(value = "/create_customer", method = RequestMethod.GET)
    public ModelAndView loadCreateForm() {
        logger.trace("Go into loadCreateForm()");
        ModelAndView createForm = null;
        CustomerEntity customer;
        try {
            customer = new CustomerEntity();
            createForm = new ModelAndView("customer/create");
            createForm.addObject("customer", customer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit loadCreateForm()");
        return createForm;
    }

    //create new customer
    @RequestMapping(value = "create_customer", method = RequestMethod.POST)
    public ModelAndView createCustomer(@ModelAttribute("customer") CustomerEntity customer, Pageable pageable, @RequestParam("s") Optional<String> s) {
        logger.trace("Go into newCustomer()");
        ModelAndView newCustomer = null;
        try {
            customerService.save(customer);
            logger.info(customer.toString());
            newCustomer = backToListCustomers(Alert.CREATE, loadListCustomer(pageable, s));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit newCustomer()");
        return newCustomer;
    }

    //load edit form
    @RequestMapping(value = "/edit_customer/{id}", method = RequestMethod.GET)
    public ModelAndView loadEditForm(@PathVariable("id") Integer id) {
        logger.trace("Go into loadEditForm()");
        ModelAndView editForm = null;
        CustomerEntity customer;
        try {
            customer = customerService.findById(id);
            logger.info(customer.toString());
            editForm = new ModelAndView("customer/edit");
            editForm.addObject("customer", customer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit loadEditForm()");
        return editForm;
    }

    //edit customer
    @RequestMapping(value = "/edit_customer", method = RequestMethod.POST)
    public ModelAndView editCustomer(@ModelAttribute("customer") CustomerEntity customer, Pageable pageable, @RequestParam("s") Optional<String> s) {
        logger.trace("Go into editCustomer()");
        ModelAndView editCustomer = null;
        try {
            customerService.save(customer);
            logger.info(customer.toString());
            editCustomer = backToListCustomers(Alert.EDIT, loadListCustomer(pageable, s));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit editCustomer()");
        return editCustomer;
    }

    //delete customer
    @RequestMapping(value = "/delete_customer/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCustomer(@PathVariable("id") Integer id, Pageable pageable, @RequestParam("s") Optional<String> s) {
        logger.trace("Go into deleteCustomer()");
        ModelAndView deleteCustomer = null;
        try {
            logger.info(customerService.findById(id).toString());
            customerService.remove(id);
            deleteCustomer = backToListCustomers(Alert.DELETE, loadListCustomer(pageable, s));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit deleteCustomer()");
        return deleteCustomer;
    }

    private ModelAndView backToListCustomers(Integer type, ModelAndView modelAndView) {
        switch (type) {
            case Alert.CREATE:
                modelAndView.addObject("alert", "Them thanh cong");
                break;
            case Alert.EDIT:
                modelAndView.addObject("alert", "Sua thanh cong");
                break;
            case Alert.DELETE:
                modelAndView.addObject("alert", "Xoa thanh cong");
                break;
        }
        return modelAndView;
    }
}