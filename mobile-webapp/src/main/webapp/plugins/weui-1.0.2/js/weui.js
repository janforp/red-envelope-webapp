+function (win, $) {
    var tips = {
        toastSelector: "#global-toast",
        loadingToastSelector: "#global-toast-loading",
        $toast: null,
        $loadingToast: null,
        defaultTimeout: 2000,
        tipsIntervalId: null,
        init: function () {
            this.$toast = $(this.toastSelector);
            if (this.$toast.length <= 0) {
                throw new Error("can not find toast element.");
            }
            this.$loadingToast = $(this.loadingToastSelector);
            if (this.$loadingToast.length <= 0) {
                throw new Error("can not find loading toast element.");
            }
        },
        loading: function (msg) {
            var self = this;
            var $toast = self.$loadingToast;
            $toast.find(".weui-toast__content").html(msg);
            if ($toast.css('display') != 'none') {
                return;
            }
            $toast.fadeIn("fast");
        },
        hideLoading: function () {
            var self = this;
            var $toast = self.$loadingToast;
            if ($toast.css('display') == 'none') {
                return;
            }
            $toast.hide();
        },
        suc: function (msg, timeout) {
            var self = this;
            var $toast = self.$toast;
            if ($toast.css('display') != 'none') {
                if (self.tipsIntervalId != null) {
                    clearInterval(self.tipsIntervalId)
                    self.tipsIntervalId = null;
                }
                $toast.hide();
            }
            var _timeout = self.defaultTimeout;
            if (timeout != null && typeof(timeout) == 'number' && (!isNaN(timeout)) && timeout > 0) {
                _timeout = timeout;
            }
            var $weui_toast_content = $toast.find(".weui-toast__content");
            if (msg == null || msg === "") {
                $weui_toast_content.hide();
            } else {
                $weui_toast_content.html(msg).show();
            }
            $toast.fadeIn("fast");
            self.tipsIntervalId = setTimeout(function () {
                self.tipsIntervalId = null;
                $toast.hide();
            }, _timeout);
        },
        hide: function () {
            var self = this;
            var $toast = self.$toast;
            if ($toast.css('display') == 'none') {
                return;
            }
            if (self.tipsIntervalId != null) {
                clearInterval(self.tipsIntervalId)
                self.tipsIntervalId = null;
            }
            $toast.hide();
        }
    };
    win.tips = tips;
    var dialog = {
        alertSelector: "#global-dialog-alert",
        confirmSelector: "#global-dialog-confirm",
        $alert: null,
        alertOriginalTitle: "&nbsp;",
        alertOriginalMsg: "&nbsp;",
        alertCallback: null,
        $confirm: null,
        confirmOriginalTitle: "&nbsp;",
        confirmOriginalMsg: "&nbsp;",
        confirmOkCallback: null,
        confirmCancelCallback: null,
        init: function () {
            var self = this;
            self.$alert = $(self.alertSelector);
            if (self.$alert.length <= 0) {
                throw new Error("can not find alert dialog element.");
            }
            self.alertOriginalTitle = self.$alert.find(".weui-dialog__title").html();
            self.alertOriginalMsg = self.$alert.find(".weui-dialog__bd").html();
            self.$alert.find(".weui-dialog__btn").on('click', function ($event) {
                self.hideAlert(this);
            });
            self.$confirm = $(self.confirmSelector);
            if (self.$confirm.length <= 0) {
                throw new Error("can not find confirm dialog element.");
            }
            self.confirmOriginalTitle = self.$confirm.find(".weui-dialog__title").html();
            self.confirmOriginalMsg = self.$confirm.find(".weui-dialog__bd").html();
            self.$confirm.find(".weui-dialog__btn").on('click', function ($event) {
                self.hideConfirm(this);
            });
        },
        /**
         * alert窗口，一般来说标题必须要有，内容可以没有
         * @param title 消息标题
         * @param msg 消息内容（灰色的字）
         * @param callback 用户关闭对话框后的回调方法
         */
        alert: function (title, msg, callback) {
            var self = this;
            var $dialog = self.$alert;
            if ($dialog.css('display') != 'none') {
                return;
            }
            if (title == null) {
                title = "";
            }
            if (msg == null) {
                msg = "";
            }
            $dialog.find(".weui-dialog__title").html(title);
            $dialog.find(".weui-dialog__bd").html(msg);
            if (callback != null && typeof(callback) == "function") {
                self.alertCallback = callback;
            }
            $dialog.fadeIn("fast");
        },
        /**
         * confirm窗口，一般来说标题必须要有，内容可以没有
         * @param title 消息标题
         * @param msg 消息内容（灰色的字）
         * @param okCallback 用户选择确定、同意后的回调方法
         * @param cancelCallback 用户选择取消、拒绝后的回调方法
         */
        confirm: function (title, msg, okCallback, cancelCallback) {
            var self = this;
            var $dialog = self.$confirm;
            if ($dialog.css('display') != 'none') {
                return;
            }
            if (title == null) {
                title = "";
            }
            if (msg == null) {
                msg = "";
            }
            $dialog.find(".weui-dialog__title").html(title);
            $dialog.find(".weui-dialog__bd").html(msg);
            if (okCallback != null && typeof(okCallback) == "function") {
                self.confirmOkCallback = okCallback;
            }
            if (cancelCallback != null && typeof(cancelCallback) == "function") {
                self.confirmCancelCallback = cancelCallback;
            }
            $dialog.fadeIn("fast");
        },
        hideAlert: function () {
            var self = this;
            self.$alert.hide();
            self.$alert.find(".weui-dialog__title").html(self.alertOriginalTitle);
            self.$alert.find(".weui-dialog__bd").html(self.alertOriginalMsg);
            if (self.alertCallback != null && typeof(self.alertCallback) == "function") {
                var callbackScope = win;
                if (arguments.length > 0 && arguments[0] != null && typeof(arguments[0]) == 'object') {
                    callbackScope = arguments[0];
                }
                self.alertCallback.call(callbackScope);
            }
            self.alertCallback = null;
        },
        hideConfirm: function () {
            var self = this;
            self.$confirm.hide();
            self.$confirm.find(".weui-dialog__title").html(self.confirmOriginalTitle);
            self.$confirm.find(".weui-dialog__bd").html(self.confirmOriginalMsg);
            if ((self.confirmOkCallback != null && typeof(self.confirmOkCallback) == "function")
                || (self.confirmCancelCallback != null && typeof(self.confirmCancelCallback) == "function")) {
                var callbackScope = win;
                var isClickOkBtn = false;
                if (arguments.length > 0 && arguments[0] != null && typeof(arguments[0]) == 'object') {
                    callbackScope = arguments[0];
                    isClickOkBtn = $(callbackScope).hasClass("weui-dialog__btn_primary");
                }
                if (isClickOkBtn) {
                    self.confirmOkCallback.call(callbackScope);
                } else {
                    self.confirmCancelCallback.call(callbackScope);
                }
            }
            self.confirmOkCallback = null;
            self.confirmCancelCallback = null;
        }
    };
    win.dialog = dialog;

    // 以下内容是document ready以后
    $(function () {
        // document ready
        tips.init();
        dialog.init();
    });

}(window, $);