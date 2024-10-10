import $ from "jquery"; // 如果还没有安装 jQuery，请运行 npm install jquery

// 获取 OSS 上传凭证
const getOssPolicy = (store) => {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: "http://localhost:3001/oss/policy", // 替换为您后端的 OSS 签名接口
      type: "GET",
      headers: {
        Authorization: "Bearer " + store.state.user.token, // 若后端需要 token 验证
      },
      success: function (response) {
        resolve(response); // 返回 OSS 上传凭证
      },
      error: function (error) {
        console.error("无法获取 OSS 签名", error);
        reject(error);
      },
    });
  });
};

// 上传图片到阿里云 OSS
export const uploadImage = async (selectedFile, store) => {
  if (!selectedFile) {
    alert("请先选择文件");
    return;
  }

  // 获取 OSS 上传凭证
  let ossData = null;
  try {
    ossData = await getOssPolicy(store);
    console.log("OSS 上传凭证:", ossData);
  } catch (error) {
    alert("无法获取 OSS 签名");
    return;
  }

  if (!ossData) {
    alert("无法获取 OSS 签名");
    return;
  }

  // 构建 FormData，携带文件和 OSS 签名信息
  const randomFileName = generateRandomFileName(selectedFile);
  const formData = new FormData();
 console.log("ossData");
 
  console.log(ossData);
  
  formData.append("key", ossData.dir + randomFileName); // 上传路径
  formData.append("OSSAccessKeyId", ossData.accessid); // Access Key ID
  formData.append("policy", ossData.policy); // 上传策略
  formData.append("Signature", ossData.signature); // 签名
  formData.append("success_action_status", "200"); // 返回状态码
  formData.append("file", selectedFile); // 上传的文件
  console.log("开始上传图片");

  // 使用 $.ajax 将图片上传到阿里云 OSS
  return new Promise((resolve, reject) => {
    $.ajax({
      url: ossData.host, // 阿里云 OSS 的上传地址
      type: "POST",
      data: formData,
      processData: false, // 告诉 jQuery 不要处理数据
      contentType: false, // 告诉 jQuery 不要设置内容类型
      success(resp) {
        console.log("图片上传成功:", resp);
        // 返回上传的响应以及所上传的文件
        resolve({ response: resp, file: ossData.dir + randomFileName });
      },
      error(resp) {
        console.error("图片上传失败:", resp);
        console.error("状态码:", resp.status);
        console.error("响应文本:", resp.responseText);
        reject(resp);
      },
    });
  });
};


// 生成随机文件名
const generateRandomFileName = (file) => {
  const randomString = Math.random().toString(36).substr(2, 9); // 生成随机字符串
  const timestamp = Date.now(); // 获取当前时间戳
  const extension = file.name.split(".").pop(); // 获取文件扩展名
  return `${randomString}_${timestamp}.${extension}`; // 组合生成文件名
};
