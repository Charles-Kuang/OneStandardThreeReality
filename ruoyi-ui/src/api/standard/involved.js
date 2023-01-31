import request from '@/utils/request'

// 查询主体涉案列表
export function listInvolved(query) {
  return request({
    url: '/standard/involved/list',
    method: 'get',
    params: query
  })
}

// 查询主体涉案详细
export function getInvolved(involvedEntityType, involvedId) {
  return request({
    url: '/standard/involved/' + involvedEntityType + '/' + involvedId,
    method: 'get'
  })
}

// 新增主体涉案
export function addInvolved(data) {
  return request({
    url: '/standard/involved',
    method: 'post',
    data: data
  })
}

// 修改主体涉案
export function updateInvolved(data) {
  return request({
    url: '/standard/involved',
    method: 'put',
    data: data
  })
}

// 删除主体涉案
export function delInvolved(involvedId) {
  return request({
    url: '/standard/involved/' + involvedId,
    method: 'delete'
  })
}
