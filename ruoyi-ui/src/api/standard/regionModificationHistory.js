import request from '@/utils/request'

// 查询行政区修改轨迹列表
export function listHistory(query) {
  return request({
    url: '/standard/history/list',
    method: 'get',
    params: query
  })
}

// 新增行政区修改轨迹
export function addHistory(data) {
  return request({
    url: '/standard/history',
    method: 'post',
    data: data
  })
}

// 删除行政区修改轨迹
export function delHistory(modificationId) {
  return request({
    url: '/standard/history/' + modificationId,
    method: 'delete'
  })
}
