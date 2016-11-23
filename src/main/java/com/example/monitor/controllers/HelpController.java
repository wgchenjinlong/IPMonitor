package com.example.monitor.controllers;

import com.example.monitor.domain.dtos.HelpDto;
import com.example.monitor.domain.forms.HelpForm;
import com.example.monitor.domain.models.Help;
import com.example.monitor.repositories.HelpRepository;
import com.example.monitor.services.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HelpController {

    @Autowired
    private HelpService helpService;

    @Autowired
    private HelpRepository helpRepository;

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public ModelAndView index(Map<String, Object> model) {

        List<HelpDto> list = new ArrayList();
        List<Help> helpList = helpService.getHelpList();

        for(Help help : helpList) {
            HelpDto helpDto = new HelpDto();
            helpDto.setId(help.getId());
            helpDto.setQuestion(help.getQuestion());
            helpDto.setAnswer(help.getAnswer());
            list.add(helpDto);
        }
        model.put("helpList", list);
        return new ModelAndView("help/index", model);
    }

    @RequestMapping(value = "/help/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(Map<String, Object> model, @PathVariable Integer id) {

        Help help = helpRepository.findOne(id);
        model.put("help", help);
        return new ModelAndView("help/show", model);
    }

    @RequestMapping(value = "/help/edit/{id}", method = RequestMethod.GET)
    public Help edit(@PathVariable Integer id) {

        Help help = helpRepository.findOne(id);
        return help;
    }

    @RequestMapping(value = "/help/add", method = RequestMethod.POST)
    public ModelAndView create(@Valid HelpForm helpForm, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            for (FieldError field: result.getFieldErrors()) {

                System.out.println(field.getDefaultMessage());
            }
            attr.addFlashAttribute("error", "添加失败");
            return new ModelAndView(new RedirectView("/help", true));
        }

        Help help = new Help();
        help.setQuestion(helpForm.getQuestion());
        help.setAnswer(helpForm.getAnswer());
        helpRepository.save(help);
        attr.addFlashAttribute("success", "添加成功");
        return new ModelAndView(new RedirectView("/help", true));
    }

    @RequestMapping(value = "/help/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid HelpForm helpForm, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            for (FieldError field: result.getFieldErrors()) {

                System.out.println(field.getDefaultMessage());
            }
            attr.addFlashAttribute("error", "更新失败");
            return new ModelAndView(new RedirectView("/help", true));
        }

        helpRepository.updateHelp(helpForm.getQuestion(), helpForm.getAnswer(), helpForm.getId());
        attr.addFlashAttribute("success", "更新成功");
        return new ModelAndView(new RedirectView("/help", true));
    }

    @RequestMapping(value = "/help/delete/{id}", method = RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable Integer id, RedirectAttributes attr) {

        if (id == null) {
            attr.addFlashAttribute("error", "删除失败");
        }
        Help help = helpRepository.findOne(id);
        if (help == null) {
            attr.addFlashAttribute("error", "删除失败");
        } else {
            helpRepository.delete(id);
            attr.addFlashAttribute("success", "删除成功");
        }

        return new ModelAndView(new RedirectView("/help"));
    }
}
