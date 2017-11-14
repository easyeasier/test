package wz.test.jdk;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.lang.ref.SoftReference;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class DescribeVerify {
    public static void main(String[] args) {
        verify();
    }

    public static void verify() {
//        Map<String, Object> desc = JSON.parseObject(getTaskStr(), Map.class);
        Map<String, Object> desc = JSON.parseObject(getStr(), Map.class);
        Map<String, Map<String, Object>> fields = (Map<String, Map<String, Object>>) desc.get("fields");
        fields.forEach((apiName, field) -> {
            String realApiName = (String) field.get("api_name");
            if (!apiName.equals(realApiName)) {
                System.out.println(field.get("label") + " ==== " + "error");
            } else {
                System.out.println(field.get("label") + " ==== " + "ok");
            }
        });
    }


    public static String getTaskStr() {
        String json = "{\n" +
                "    \"_id\": \"5a0467952848bd9588095ef7\",\n" +
                "    \"index_version\": 1,\n" +
                "    \"is_active\": true,\n" +
                "    \"package\": \"CRM\",\n" +
                "    \"api_name\": \"BpmInstance\",\n" +
                "    \"description\": \"业务流程\",\n" +
                "    \"define_type\": \"package\",\n" +
                "    \"visible_scope\": \"bi\",\n" +
                "    \"is_deleted\": false,\n" +
                "    \"display_name\": \"业务流程\",\n" +
                "    \"fields\": {\n" +
                "      \"sourceWorkflowId\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"sourceWorkflowId\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程srcId\",\n" +
                "        \"max_length\": 256,\n" +
                "        \"pattern\": \"\",\n" +
                "        \"index_name\": \"string_1\"\n" +
                "      },\n" +
                "      \"name\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"name\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"system\",\n" +
                "        \"description\": \"name\",\n" +
                "        \"label\": \"流程主题\",\n" +
                "        \"max_length\": 256,\n" +
                "        \"pattern\": \"\",\n" +
                "        \"index_name\": \"name\",\n" +
                "        \"resource_bundle_key\": \"BpmInstance.name\",\n" +
                "        \"status\": \"released\"\n" +
                "      },\n" +
                "      \"workflowName\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"workflowName\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程名称\",\n" +
                "        \"max_length\": 256,\n" +
                "        \"pattern\": \"\",\n" +
                "        \"index_name\": \"string_2\"\n" +
                "      },\n" +
                "      \"relatedObject\": {\n" +
                "        \"type\": \"group\",\n" +
                "        \"group_type\": \"what\",\n" +
                "        \"api_name\": \"relatedObject\",\n" +
                "        \"is_required\": false,\n" +
                "        \"help_text\": \"\",\n" +
                "        \"label\": \"发起对象\",\n" +
                "        \"is_unique\": true,\n" +
                "        \"status\": \"new\",\n" +
                "        \"is_index\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"is_active\": true,\n" +
                "        \"is_readonly\": false,\n" +
                "        \"description\": \"流程发起对象,包含对象类型和对象数据\",\n" +
                "        \"fields\": {\n" +
                "          \"id_field\": \"objectDataId\",\n" +
                "          \"api_name_field\": \"objectApiName\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"objectApiName\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"objectApiName\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程发起对象\",\n" +
                "        \"max_length\": 256,\n" +
                "        \"pattern\": \"\",\n" +
                "        \"index_name\": \"string_3\"\n" +
                "      },\n" +
                "      \"objectDataId\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"objectDataId\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程发起记录\",\n" +
                "        \"max_length\": 256,\n" +
                "        \"pattern\": \"\",\n" +
                "        \"index_name\": \"string_4\"\n" +
                "      },\n" +
                "      \"stageNames\": {\n" +
                "        \"type\": \"tag\",\n" +
                "        \"is_index\": false,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"stageNames\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"当前阶段\"\n" +
                "      },\n" +
                "      \"workflowId\": {\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"workflowId\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程定义Id\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"max_length\": 256,\n" +
                "        \"pattern\": \"\",\n" +
                "        \"index_name\": \"string_5\"\n" +
                "      },\n" +
                "      \"startTime\": {\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"startTime\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程发起时间\",\n" +
                "        \"type\": \"date_time\",\n" +
                "        \"time_zone\": \"GMT+8\",\n" +
                "        \"date_format\": \"yyyy-MM-dd HH:mm:ss\",\n" +
                "        \"index_name\": \"date_1\"\n" +
                "      },\n" +
                "      \"endTime\": {\n" +
                "        \"is_index\": false,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"endTime\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程结束时间\",\n" +
                "        \"type\": \"date_time\",\n" +
                "        \"time_zone\": \"GMT+8\",\n" +
                "        \"date_format\": \"yyyy-MM-dd HH:mm:ss\"\n" +
                "      },\n" +
                "      \"lastModifyTime\": {\n" +
                "        \"is_index\": false,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"lastModifyTime\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"最后更新时间\",\n" +
                "        \"type\": \"date_time\",\n" +
                "        \"time_zone\": \"GMT+8\",\n" +
                "        \"date_format\": \"yyyy-MM-dd HH:mm:ss\"\n" +
                "      },\n" +
                "      \"applicantId\": {\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"applicantId\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程发起人\",\n" +
                "        \"is_single\": false,\n" +
                "        \"type\": \"employee\",\n" +
                "        \"index_name\": \"stringList_1\"\n" +
                "      },\n" +
                "      \"lastModifyBy\": {\n" +
                "        \"is_index\": false,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"lastModifyBy\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"最后更新人\",\n" +
                "        \"is_single\": false,\n" +
                "        \"type\": \"employee\"\n" +
                "      },\n" +
                "      \"taskNames\": {\n" +
                "        \"is_index\": false,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"taskNames\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"当前任务\",\n" +
                "        \"type\": \"tag\"\n" +
                "      },\n" +
                "      \"objectIds\": {\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"objectIds\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"节点流转对象Id\",\n" +
                "        \"type\": \"tag\",\n" +
                "        \"index_name\": \"string_6\"\n" +
                "      },\n" +
                "      \"duration\": {\n" +
                "        \"is_index\": false,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"duration\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程耗时\",\n" +
                "        \"type\": \"number\",\n" +
                "        \"decimal_places\": 0,\n" +
                "        \"length\": 15,\n" +
                "        \"round_mode\": 4\n" +
                "      },\n" +
                "      \"state\": {\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"state\",\n" +
                "        \"options\": [\n" +
                "          {\n" +
                "            \"label\": \"进行中\",\n" +
                "            \"value\": \"in_progress\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"label\": \"已完成\",\n" +
                "            \"value\": \"pass\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"label\": \"已终止\",\n" +
                "            \"value\": \"cancel\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"label\": \"异常\",\n" +
                "            \"value\": \"error\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"流程状态\",\n" +
                "        \"type\": \"select_one\",\n" +
                "        \"index_name\": \"string_7\"\n" +
                "      },\n" +
                "      \"triggerSource\": {\n" +
                "        \"is_index\": true,\n" +
                "        \"is_active\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"api_name\": \"triggerSource\",\n" +
                "        \"options\": [\n" +
                "          {\n" +
                "            \"label\": \"手动发起\",\n" +
                "            \"value\": \"person\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"label\": \"业务流程触发\",\n" +
                "            \"value\": \"bpm\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"label\": \"工作流触发\",\n" +
                "            \"value\": \"workflow\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"label\": \"审批流触发\",\n" +
                "            \"value\": \"approval\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"is_unique\": false,\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"label\": \"发起类型\",\n" +
                "        \"type\": \"select_one\",\n" +
                "        \"index_name\": \"string_8\"\n" +
                "      },\n" +
                "      \"owner\": {\n" +
                "        \"type\": \"employee\",\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"is_index\": false,\n" +
                "        \"is_need_convert\": true,\n" +
                "        \"round_mode\": 4,\n" +
                "        \"length\": 8,\n" +
                "        \"decimal_places\": 0,\n" +
                "        \"label\": \"负责人\",\n" +
                "        \"api_name\": \"owner\",\n" +
                "        \"is_unique\": false,\n" +
                "        \"description\": \"负责人\",\n" +
                "        \"status\": \"released\",\n" +
                "        \"is_single\": true,\n" +
                "        \"is_required\": false,\n" +
                "        \"index_name\": \"stringList_2\"\n" +
                "      },\n" +
                "      \"relevant_team\": {\n" +
                "        \"type\": \"embedded_object_list\",\n" +
                "        \"define_type\": \"package\",\n" +
                "        \"is_index\": true,\n" +
                "        \"is_need_convert\": false,\n" +
                "        \"is_required\": false,\n" +
                "        \"is_unique\": false,\n" +
                "        \"embedded_fields\": {\n" +
                "          \"teamMemberEmployee\": {\n" +
                "            \"type\": \"employee\",\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_need_convert\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"is_unique\": false,\n" +
                "            \"is_single\": true,\n" +
                "            \"api_name\": \"teamMemberEmployee\",\n" +
                "            \"description\": \"成员员工\",\n" +
                "            \"help_text\": \"成员员工\",\n" +
                "            \"label\": \"成员员工\",\n" +
                "            \"index_name\": \"stringList_3\"\n" +
                "          },\n" +
                "          \"teamMemberRole\": {\n" +
                "            \"type\": \"select_one\",\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_need_convert\": false,\n" +
                "            \"is_required\": false,\n" +
                "            \"is_unique\": false,\n" +
                "            \"options\": [\n" +
                "              {\n" +
                "                \"label\": \"负责人\",\n" +
                "                \"value\": \"1\",\n" +
                "                \"resource_bundle_key\": null\n" +
                "              },\n" +
                "              {\n" +
                "                \"label\": \"普通成员\",\n" +
                "                \"value\": \"4\",\n" +
                "                \"resource_bundle_key\": null\n" +
                "              }\n" +
                "            ],\n" +
                "            \"api_name\": \"teamMemberRole\",\n" +
                "            \"description\": \"成员角色\",\n" +
                "            \"help_text\": \"成员角色\",\n" +
                "            \"label\": \"成员角色\",\n" +
                "            \"index_name\": \"string_9\"\n" +
                "          },\n" +
                "          \"teamMemberPermissionType\": {\n" +
                "            \"type\": \"select_one\",\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_need_convert\": false,\n" +
                "            \"is_required\": false,\n" +
                "            \"is_unique\": false,\n" +
                "            \"options\": [\n" +
                "              {\n" +
                "                \"label\": \"只读\",\n" +
                "                \"value\": \"1\",\n" +
                "                \"resource_bundle_key\": null\n" +
                "              },\n" +
                "              {\n" +
                "                \"label\": \"读写\",\n" +
                "                \"value\": \"2\",\n" +
                "                \"resource_bundle_key\": null\n" +
                "              }\n" +
                "            ],\n" +
                "            \"api_name\": \"teamMemberPermissionType\",\n" +
                "            \"description\": \"成员权限类型\",\n" +
                "            \"help_text\": \"成员权限类型\",\n" +
                "            \"label\": \"成员权限类型\",\n" +
                "            \"index_name\": \"string_10\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"label\": \"相关团队\",\n" +
                "        \"help_text\": \"相关团队\",\n" +
                "        \"api_name\": \"relevant_team\",\n" +
                "        \"index_name\": \"embeddedList_1\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"tenant_id\": \"53395\",\n" +
                "    \"version\": 1,\n" +
                "    \"last_modified_by\": null,\n" +
                "    \"last_modified_time\": \"2017-11-09T14:35:01.739+0000\",\n" +
                "    \"revision\": 6\n" +
                "  }";
        return json;
    }

    public static String getStr(){
        String json = "{\n" +
                "    \"_id\": \"5a0467962848bd9588095ef8\",\n" +
                "    \"index_version\": 1,\n" +
                "    \"is_active\": true,\n" +
                "    \"package\": \"CRM\",\n" +
                "    \"api_name\": \"BpmTask\",\n" +
                "    \"description\": \"业务流程任务\",\n" +
                "    \"define_type\": \"package\",\n" +
                "    \"visible_scope\": \"bi\",\n" +
                "    \"is_deleted\": false,\n" +
                "    \"display_name\": \"业务流程任务\",\n" +
                "    \"fields\": {\n" +
                "        \"sourceWorkflowId\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"sourceWorkflowId\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"流程srcId\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"string_1\"\n" +
                "        },\n" +
                "        \"name\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"name\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"system\",\n" +
                "            \"description\": \"name\",\n" +
                "            \"label\": \"任务主题\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"name\",\n" +
                "            \"resource_bundle_key\": \"BpmTask.name\",\n" +
                "            \"status\": \"released\"\n" +
                "        },\n" +
                "        \"relatedObject\": {\n" +
                "            \"type\": \"group\",\n" +
                "            \"group_type\": \"what\",\n" +
                "            \"api_name\": \"relatedObject\",\n" +
                "            \"is_required\": false,\n" +
                "            \"help_text\": \"\",\n" +
                "            \"label\": \"所属对象\",\n" +
                "            \"is_unique\": true,\n" +
                "            \"status\": \"new\",\n" +
                "            \"is_index\": true,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"is_active\": true,\n" +
                "            \"is_readonly\": false,\n" +
                "            \"description\": \"所属对象,包含对象类型和对象数据\",\n" +
                "            \"fields\": {\n" +
                "                \"id_field\": \"objectDataId\",\n" +
                "                \"api_name_field\": \"objectApiName\"\n" +
                "            },\n" +
                "            \"index_name\": \"string_11\"\n" +
                "        },\n" +
                "        \"taskName\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"taskName\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"任务名称\",\n" +
                "            \"type\": \"text\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"string_12\"\n" +
                "        },\n" +
                "        \"activityId\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"activityId\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"流程节点定义Id\",\n" +
                "            \"type\": \"text\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"string_3\"\n" +
                "        },\n" +
                "        \"workflowInstanceName\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"workflowInstanceName\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"流程主题\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"string_4\"\n" +
                "        },\n" +
                "        \"objectApiName\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"objectApiName\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"所属业务对象\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"string_5\"\n" +
                "        },\n" +
                "        \"objectDataId\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"objectDataId\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"所属业务记录\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"string_6\"\n" +
                "        },\n" +
                "        \"stageName\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"stageName\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"当前阶段\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"string_7\"\n" +
                "        },\n" +
                "        \"stageId\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"stageId\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"当前阶段Id\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"string_13\"\n" +
                "        },\n" +
                "        \"workflowId\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"workflowId\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"流程定义Id\",\n" +
                "            \"type\": \"text\",\n" +
                "            \"max_length\": 256,\n" +
                "            \"pattern\": \"\",\n" +
                "            \"index_name\": \"string_9\"\n" +
                "        },\n" +
                "        \"workflowInstanceId\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"workflowInstanceId\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"所属业务流程\",\n" +
                "            \"type\": \"object_reference\",\n" +
                "            \"target_api_name\": \"BpmInstance\",\n" +
                "            \"action_on_target_delete\": \"do_not_allow\",\n" +
                "            \"target_related_list_label\": \"任务\",\n" +
                "            \"target_related_list_name\": \"instance_task\",\n" +
                "            \"index_name\": \"string_10\"\n" +
                "        },\n" +
                "        \"startTime\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"startTime\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"任务开始时间\",\n" +
                "            \"type\": \"date_time\",\n" +
                "            \"time_zone\": \"GMT+8\",\n" +
                "            \"date_format\": \"yyyy-MM-dd HH:mm:ss\",\n" +
                "            \"index_name\": \"date_1\"\n" +
                "        },\n" +
                "        \"endTime\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"endTime\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"任务结束时间\",\n" +
                "            \"type\": \"date_time\",\n" +
                "            \"time_zone\": \"GMT+8\",\n" +
                "            \"date_format\": \"yyyy-MM-dd HH:mm:ss\",\n" +
                "            \"index_name\": \"date_2\"\n" +
                "        },\n" +
                "        \"processorIds\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"processorIds\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"任务执行人\",\n" +
                "            \"is_single\": false,\n" +
                "            \"type\": \"employee\",\n" +
                "            \"index_name\": \"stringList_1\"\n" +
                "        },\n" +
                "        \"objectIds\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"objectIds\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"节点流转对象Id\",\n" +
                "            \"type\": \"tag\",\n" +
                "            \"index_name\": \"string_11\"\n" +
                "        },\n" +
                "        \"duration\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"duration\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"任务耗时\",\n" +
                "            \"type\": \"number\",\n" +
                "            \"decimal_places\": 0,\n" +
                "            \"length\": 15,\n" +
                "            \"round_mode\": 4,\n" +
                "            \"index_name\": \"double_0\"\n" +
                "        },\n" +
                "        \"remindLatency\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"remindLatency\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"允许停留时长\",\n" +
                "            \"type\": \"number\",\n" +
                "            \"decimal_places\": 0,\n" +
                "            \"length\": 15,\n" +
                "            \"round_mode\": 4,\n" +
                "            \"index_name\": \"double_1\"\n" +
                "        },\n" +
                "        \"timeoutTime\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"timeoutTime\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"超时时长\",\n" +
                "            \"type\": \"number\",\n" +
                "            \"decimal_places\": 0,\n" +
                "            \"length\": 15,\n" +
                "            \"round_mode\": 4,\n" +
                "            \"index_name\": \"double_2\"\n" +
                "        },\n" +
                "        \"isTimeout\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"isTimeout\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"是否超时\",\n" +
                "            \"type\": \"true_or_false\",\n" +
                "            \"default_value\": false,\n" +
                "            \"index_name\": \"boolean_1\"\n" +
                "        },\n" +
                "        \"state\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"state\",\n" +
                "            \"options\": [\n" +
                "                {\n" +
                "                    \"label\": \"进行中\",\n" +
                "                    \"value\": \"in_progress\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"已完成\",\n" +
                "                    \"value\": \"pass\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"已终止\",\n" +
                "                    \"value\": \"cancel\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"异常\",\n" +
                "                    \"value\": \"error\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"任务状态\",\n" +
                "            \"type\": \"select_one\",\n" +
                "            \"index_name\": \"string_12\"\n" +
                "        },\n" +
                "        \"assignees\": {\n" +
                "            \"is_index\": true,\n" +
                "            \"is_active\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"api_name\": \"assignees\",\n" +
                "            \"embedded_fields\": {\n" +
                "                \"person\": {\n" +
                "                    \"is_index\": false,\n" +
                "                    \"is_active\": true,\n" +
                "                    \"is_required\": false,\n" +
                "                    \"api_name\": \"person\",\n" +
                "                    \"is_unique\": false,\n" +
                "                    \"define_type\": \"package\",\n" +
                "                    \"label\": \"处理人\",\n" +
                "                    \"type\": \"tag\",\n" +
                "                    \"index_name\": \"string_14\"\n" +
                "                },\n" +
                "                \"dept\": {\n" +
                "                    \"is_index\": false,\n" +
                "                    \"is_active\": true,\n" +
                "                    \"is_required\": false,\n" +
                "                    \"api_name\": \"dept\",\n" +
                "                    \"is_unique\": false,\n" +
                "                    \"define_type\": \"package\",\n" +
                "                    \"label\": \"部门\",\n" +
                "                    \"type\": \"tag\",\n" +
                "                    \"index_name\": \"string_15\"\n" +
                "                },\n" +
                "                \"dept_leader\": {\n" +
                "                    \"is_index\": false,\n" +
                "                    \"is_active\": true,\n" +
                "                    \"is_required\": false,\n" +
                "                    \"api_name\": \"dept_leader\",\n" +
                "                    \"is_unique\": false,\n" +
                "                    \"define_type\": \"package\",\n" +
                "                    \"label\": \"部门领导人\",\n" +
                "                    \"type\": \"tag\",\n" +
                "                    \"index_name\": \"string_16\"\n" +
                "                },\n" +
                "                \"group\": {\n" +
                "                    \"is_index\": false,\n" +
                "                    \"is_active\": true,\n" +
                "                    \"is_required\": false,\n" +
                "                    \"api_name\": \"group\",\n" +
                "                    \"is_unique\": false,\n" +
                "                    \"define_type\": \"package\",\n" +
                "                    \"label\": \"组\",\n" +
                "                    \"type\": \"tag\",\n" +
                "                    \"index_name\": \"string_17\"\n" +
                "                },\n" +
                "                \"role\": {\n" +
                "                    \"is_index\": false,\n" +
                "                    \"is_active\": true,\n" +
                "                    \"is_required\": false,\n" +
                "                    \"api_name\": \"role\",\n" +
                "                    \"is_unique\": false,\n" +
                "                    \"define_type\": \"package\",\n" +
                "                    \"label\": \"角色\",\n" +
                "                    \"type\": \"tag\",\n" +
                "                    \"index_name\": \"string_18\"\n" +
                "                },\n" +
                "                \"ext_bpm\": {\n" +
                "                    \"is_index\": false,\n" +
                "                    \"is_active\": true,\n" +
                "                    \"is_required\": false,\n" +
                "                    \"api_name\": \"ext_bpm\",\n" +
                "                    \"is_unique\": false,\n" +
                "                    \"define_type\": \"package\",\n" +
                "                    \"label\": \"流程相关人员\",\n" +
                "                    \"type\": \"tag\",\n" +
                "                    \"index_name\": \"string_19\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"is_unique\": false,\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"label\": \"流程定义处理人\",\n" +
                "            \"type\": \"embedded_object\",\n" +
                "            \"index_name\": \"embeddedList_1\"\n" +
                "        },\n" +
                "        \"owner\": {\n" +
                "            \"type\": \"employee\",\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_need_convert\": true,\n" +
                "            \"round_mode\": 4,\n" +
                "            \"length\": 8,\n" +
                "            \"decimal_places\": 0,\n" +
                "            \"label\": \"负责人\",\n" +
                "            \"api_name\": \"owner\",\n" +
                "            \"is_unique\": false,\n" +
                "            \"description\": \"负责人\",\n" +
                "            \"status\": \"released\",\n" +
                "            \"is_single\": true,\n" +
                "            \"is_required\": false,\n" +
                "            \"index_name\": \"stringList_2\"\n" +
                "        },\n" +
                "        \"relevant_team\": {\n" +
                "            \"type\": \"embedded_object_list\",\n" +
                "            \"define_type\": \"package\",\n" +
                "            \"is_index\": true,\n" +
                "            \"is_need_convert\": false,\n" +
                "            \"is_required\": false,\n" +
                "            \"is_unique\": false,\n" +
                "            \"embedded_fields\": {\n" +
                "                \"teamMemberEmployee\": {\n" +
                "                    \"type\": \"employee\",\n" +
                "                    \"define_type\": \"package\",\n" +
                "                    \"is_index\": true,\n" +
                "                    \"is_need_convert\": true,\n" +
                "                    \"is_required\": false,\n" +
                "                    \"is_unique\": false,\n" +
                "                    \"is_single\": true,\n" +
                "                    \"api_name\": \"teamMemberEmployee\",\n" +
                "                    \"description\": \"成员员工\",\n" +
                "                    \"help_text\": \"成员员工\",\n" +
                "                    \"label\": \"成员员工\",\n" +
                "                    \"index_name\": \"stringList_3\"\n" +
                "                },\n" +
                "                \"teamMemberRole\": {\n" +
                "                    \"type\": \"select_one\",\n" +
                "                    \"define_type\": \"package\",\n" +
                "                    \"is_index\": true,\n" +
                "                    \"is_need_convert\": false,\n" +
                "                    \"is_required\": false,\n" +
                "                    \"is_unique\": false,\n" +
                "                    \"options\": [\n" +
                "                        {\n" +
                "                            \"label\": \"负责人\",\n" +
                "                            \"value\": \"1\",\n" +
                "                            \"resource_bundle_key\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"普通成员\",\n" +
                "                            \"value\": \"4\",\n" +
                "                            \"resource_bundle_key\": null\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"api_name\": \"teamMemberRole\",\n" +
                "                    \"description\": \"成员角色\",\n" +
                "                    \"help_text\": \"成员角色\",\n" +
                "                    \"label\": \"成员角色\",\n" +
                "                    \"index_name\": \"string_13\"\n" +
                "                },\n" +
                "                \"teamMemberPermissionType\": {\n" +
                "                    \"type\": \"select_one\",\n" +
                "                    \"define_type\": \"package\",\n" +
                "                    \"is_index\": true,\n" +
                "                    \"is_need_convert\": false,\n" +
                "                    \"is_required\": false,\n" +
                "                    \"is_unique\": false,\n" +
                "                    \"options\": [\n" +
                "                        {\n" +
                "                            \"label\": \"只读\",\n" +
                "                            \"value\": \"1\",\n" +
                "                            \"resource_bundle_key\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"读写\",\n" +
                "                            \"value\": \"2\",\n" +
                "                            \"resource_bundle_key\": null\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"api_name\": \"teamMemberPermissionType\",\n" +
                "                    \"description\": \"成员权限类型\",\n" +
                "                    \"help_text\": \"成员权限类型\",\n" +
                "                    \"label\": \"成员权限类型\",\n" +
                "                    \"index_name\": \"string_14\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"label\": \"相关团队\",\n" +
                "            \"help_text\": \"相关团队\",\n" +
                "            \"api_name\": \"relevant_team\",\n" +
                "            \"index_name\": \"embeddedList_1\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"tenant_id\": \"53395\",\n" +
                "    \"version\": 1,\n" +
                "    \"last_modified_by\": null,\n" +
                "    \"last_modified_time\": \"2017-11-09T14:35:02.264+0000\",\n" +
                "    \"revision\": 6\n" +
                "}";

        return json;
    }
}