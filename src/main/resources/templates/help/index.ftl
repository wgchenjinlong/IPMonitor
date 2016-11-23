<#import "../layout.ftl" as l>
<#import "../lib.ftl" as lib>
<#assign header>
</#assign>
<#assign footer>
<link href="/css/help/help.css" rel="stylesheet">
<script src="/js/help/help.js"></script>
</#assign>
<@l.layout title="帮助" header=header footer=footer>
<h2 class="sub-header">帮助信息</h2>
<div class="text-right btn-toolbar row">
    <div class="col-md-1 text-left">
        <span class="glyphicon glyphicon-volume-up sound-icon"
              style="font-size: 27px;color: #1A1806;display: none;"></span>
    </div>
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addModal">
        <i class="glyphicon glyphicon-plus"></i> 添加
    </button>
</div>
    <#list helpList as help>
    <div class="row help-row">
        <div class="col-xs-12 col-md-8"><a href="/help/show/${(help.id)!}">${help.question}</a></div>
        <div class="col-xs-6 col-md-4">
            <a href="javascript:void(0);" data-id="${(help.id)!}" class="edit-help">编辑</a>
            <a href="#" data-id="${(help.id)!}" data-toggle="modal"
               data-target="#deleteModal">删除</a>
        </div>
    </div>
    </#list>
</div>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="addModalLabel">添加帮助信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add-help-form" data-parsley-validate>
                    <div class="form-group">
                        <label class="col-md-3 control-label asterisk" for="question">标题</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="question" name="question"
                                   required
                                   maxlength="500">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="answer">解决方法</label>
                        <div class="col-md-7">
                            <textarea class="form-control" rows="9" id="answer" name="answer"
                                      maxlength="10000"
                                      required
                                      data-parsley-maxlength="10000"></textarea>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="save">保存</button>
            </div>
        </div>
    </div>
</div>
    <@lib.deleteDialog baseUrl="/help/delete"/>
</@l.layout>