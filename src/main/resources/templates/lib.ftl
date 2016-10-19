<#macro deleteDialog id="deleteModal" baseUrl="" text="">
<script>
        <#if baseUrl?has_content>
        $(function () {
            var formExecute = function (scope) {
                var $methodInput = $("<input>").attr("type", "hidden").attr("name", "_method").attr("value", "delete");
                var $nextUrlInput = $("<input>").attr("type", "hidden").attr("name", "nextUrl").attr("value", document.URL);
                $("<form>").attr("action", "${baseUrl}/" + scope.data("id")).attr("method", "post").append($methodInput).append($nextUrlInput).appendTo($("body")).submit();
            };

            $('#' + '${id}').on('shown.bs.modal', function (event) {
                $(this).find('.modal-delete').on('click', function () {
                    $(this).prop('disabled', true);
                    formExecute($(event.relatedTarget));
                })
            });
        });
        </#if>
</script>
<div class="modal fade" id="${id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h4>${(text?length == 0)?string('确定要删除吗?', text)}
                </h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary modal-delete">确定</button>
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro error for="" message="">
<#--<@validationError field=for>-->
    <#if message != "">
    <ul class="parsley-errors-list filled urp-errors">
        <li class="parsley-required">
        ${message}
        </li>
    </ul>
    </#if>
<#--</@validationError>-->
</#macro>