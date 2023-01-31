import request from '@/utils/request'

// 查询咨询会话列表
export function listConsultation(query) {
  return request({
    url: '/standard/consultation/list',
    method: 'get',
    params: query
  })
}

// 查询咨询会话详细
export function getConsultation(consultationId) {
  return request({
    url: '/standard/consultation/' + consultationId,
    method: 'get'
  })
}

// 新增咨询会话
export function addConsultation(data) {
  return request({
    url: '/standard/consultation',
    method: 'post',
    data: data
  })
}

// 修改咨询会话
export function updateConsultation(data) {
  return request({
    url: '/standard/consultation',
    method: 'put',
    data: data
  })
}

// 删除咨询会话
export function delConsultation(consultationId) {
  return request({
    url: '/standard/consultation/' + consultationId,
    method: 'delete'
  })
}

// 结束咨询对话
export function endConsultation(consultationId) {
    return request({
        url: '/standard/consultation/end/' + consultationId,
        method: 'get'
    })
}
