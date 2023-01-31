import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

export function listTask(query) {
    return request({
        url: '/task/list',
        method: 'get',
        params: query
    })
}