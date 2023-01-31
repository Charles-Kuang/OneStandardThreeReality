import request from '@/utils/request'

// 查询房屋日常检查列表
export function listCheck(query) {
  return request({
    url: '/standard/check/list',
    method: 'get',
    params: query
  })
}

// 查询房屋日常检查详细
export function getCheck(checkId) {
  return request({
    url: '/standard/check/' + checkId,
    method: 'get'
  })
}

// 新增房屋日常检查
export function addCheck(data) {
  return request({
    url: '/standard/check',
    method: 'post',
    data: data
  })
}

// 修改房屋日常检查
export function updateCheck(data) {
  return request({
    url: '/standard/check',
    method: 'put',
    data: data
  })
}

// 删除房屋日常检查
export function delCheck(checkId) {
  return request({
    url: '/standard/check/' + checkId,
    method: 'delete'
  })
}
