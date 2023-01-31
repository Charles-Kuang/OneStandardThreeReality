import request from '@/utils/request'

// 查询消息记录列表
export function listRecord(query) {
  return request({
    url: '/standard/record/list',
    method: 'get',
    params: query
  })
}

// 查询消息记录详细
export function getRecord(messageId) {
  return request({
    url: '/standard/record/' + messageId,
    method: 'get'
  })
}

// 新增消息记录
export function addRecord(data) {
  return request({
    url: '/standard/record',
    method: 'post',
    data: data
  })
}

// 修改消息记录
export function updateRecord(data) {
  return request({
    url: '/standard/record',
    method: 'put',
    data: data
  })
}

// 删除消息记录
export function delRecord(messageId) {
  return request({
    url: '/standard/record/' + messageId,
    method: 'delete'
  })
}
